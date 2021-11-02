package com.view.githubrepositories.domain.users

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.ui.users.UiGithubUser


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
data class DomainGithubUser(
    private val name: String,
    private val bio: String,
    private val profileImageUrl: String,
    private val isCollapsed: Boolean
) : Abstract.Object.Ui.GithubUser<UiGithubUser> {

    override fun map(mapper: Abstract.UserMapper<UiGithubUser>): UiGithubUser
        = mapper.map(name,bio,profileImageUrl,isCollapsed)

}