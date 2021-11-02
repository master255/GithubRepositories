package com.view.githubrepositories.ui.repositories.download

import com.view.githubrepositories.ui.repositories.GithubRepositoryViewModel
import com.view.githubrepositories.ui.repositories.UiGithubRepositoryState


/**
 * @author Master255 on 06.09.2021
 * masters@inbox.ru
 */

interface TempRepository {

    fun download(viewModel: GithubRepositoryViewModel<UiGithubRepositoryState>)

    fun newTempRepository(owner: String, repo: String) : Base

    data class Base(
        private val owner: String,
        private val repo: String
    ) : TempRepository{

        override fun download(viewModel: GithubRepositoryViewModel<UiGithubRepositoryState>) {
            if (isNotEmpty()) {
                viewModel.downloadRepository(owner, repo)
            }
        }

        override fun newTempRepository(owner: String, repo: String): Base
            = Base(owner, repo)

        private fun isNotEmpty() = owner.isNotEmpty() && repo.isNotEmpty()
    }
}
