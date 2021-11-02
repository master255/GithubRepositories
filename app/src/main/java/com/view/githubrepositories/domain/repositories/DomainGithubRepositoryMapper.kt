package com.view.githubrepositories.domain.repositories

import com.view.githubrepositories.core.Abstract


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
class DomainGithubRepositoryMapper : Abstract.RepositoryMapper<DomainGithubRepository> {


    override fun map(
        name: String,
        private: Boolean,
        language: String,
        owner: String,
        urlRepository: String,
        defaultBranch: String,
        isCollapsed: Boolean
    ): DomainGithubRepository
        = DomainGithubRepository(name, private, language, owner, urlRepository, defaultBranch, isCollapsed)
}