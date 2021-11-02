package com.view.githubrepositories.domain.repositories

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.ui.repositories.UiGithubRepository


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
data class DomainGithubRepository(
    private val name: String,
    private val private: Boolean,
    private val language: String,
    private val owner: String,
    private val urlRepository: String,
    private val defaultBranch: String,
    private val isCollapsed: Boolean
) : Abstract.Object.Ui.GithubRepository<UiGithubRepository> {

    override fun map(mapper: Abstract.RepositoryMapper<UiGithubRepository>): UiGithubRepository
         = mapper.map(name,private,language,owner,urlRepository, defaultBranch, isCollapsed)

}