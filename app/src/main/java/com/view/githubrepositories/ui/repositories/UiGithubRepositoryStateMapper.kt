package com.view.githubrepositories.ui.repositories

import com.view.githubrepositories.core.Abstract


/**
 * @author Master255 on 22.08.2021
 * masters@inbox.ru
 */
class UiGithubRepositoryStateMapper : Abstract.RepositoryMapper<UiGithubRepositoryState> {

    override fun map(
        name: String,
        private: Boolean,
        language: String,
        owner: String,
        urlRepository: String,
        defaultBranch: String,
        isCollapsed: Boolean
    ): UiGithubRepositoryState
        = UiGithubRepositoryState.Base(
            UiGithubRepository(name, private, language, owner, urlRepository, defaultBranch, isCollapsed),
            isCollapsed
        )
}