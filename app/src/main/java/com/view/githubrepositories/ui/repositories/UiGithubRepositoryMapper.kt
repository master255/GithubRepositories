package com.view.githubrepositories.ui.repositories

import com.view.githubrepositories.core.Abstract


/**
 * @author Master255 on 21.08.2021
 * masters@inbox.ru
 */
class UiGithubRepositoryMapper : Abstract.RepositoryMapper<UiGithubRepository> {

    override fun map(
        name: String,
        private: Boolean,
        language: String,
        owner: String,
        urlRepository: String,
        defaultBranch: String,
        isCollapsed: Boolean
    ): UiGithubRepository
        = UiGithubRepository(name, private, language, owner, urlRepository, defaultBranch, isCollapsed)
}