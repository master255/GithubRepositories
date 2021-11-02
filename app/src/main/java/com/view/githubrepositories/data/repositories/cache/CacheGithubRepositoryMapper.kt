package com.view.githubrepositories.data.repositories.cache

import com.view.githubrepositories.core.Abstract

/**
 * @author Master255 on 23.08.2021
 * masters@inbox.ru
 */
class CacheGithubRepositoryMapper : Abstract.RepositoryMapper<CacheGithubRepository> {

    override fun map(
        name: String,
        private: Boolean,
        language: String,
        owner: String,
        urlRepository: String,
        defaultBranch: String,
        isCollapsed: Boolean
    ): CacheGithubRepository
        = CacheGithubRepository(name,private,language,owner,urlRepository, defaultBranch, isCollapsed)
}