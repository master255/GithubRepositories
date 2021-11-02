package com.view.githubrepositories.data.users

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.domain.users.DomainGithubUser


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
data class DataGithubUser(
    private val name: String,
    private val bio: String,
    private val profileImageUrl: String,
    private val isCollapsed: Boolean
) : Abstract.Object.Domain.GithubUser {

    override fun map(mapper: Abstract.UserMapper<DomainGithubUser>): DomainGithubUser
        = mapper.map(name,bio,profileImageUrl,isCollapsed)
}