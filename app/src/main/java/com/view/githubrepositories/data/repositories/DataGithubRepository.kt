package com.view.githubrepositories.data.repositories

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.domain.repositories.DomainGithubRepository


/**
 * @author Master255 on 21.08.2021
 * masters@inbox.ru
 */
class DataGithubRepository(
    private val name: String,
    private val private: Boolean,
    private val language: String,
    private val owner: String,
    private val urlRepository: String,
    private val defaultBranch: String,
    private val isCollapsed: Boolean
) : Abstract.Object.Domain.GithubRepository {

    override fun map(mapper: Abstract.RepositoryMapper<DomainGithubRepository>): DomainGithubRepository
        = mapper.map(name, private, language,owner,urlRepository,defaultBranch,isCollapsed)

}