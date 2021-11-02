package com.view.githubrepositories.data.repositories

import com.view.githubrepositories.core.Abstract


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
class DataGithubRepositoryMapper : Abstract.RepositoryMapper<DataGithubRepository> {

    override fun map(
        name: String,
        private: Boolean,
        language: String,
        owner: String,
        urlRepository: String,
        defaultBranch: String,
        isCollapsed: Boolean
    ): DataGithubRepository
    {
        return DataGithubRepository(name, private, language, owner, urlRepository, defaultBranch, isCollapsed)
    }
}