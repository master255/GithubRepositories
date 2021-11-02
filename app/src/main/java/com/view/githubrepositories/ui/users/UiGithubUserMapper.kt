package com.view.githubrepositories.ui.users

import com.view.githubrepositories.core.Abstract


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
class UiGithubUserMapper : Abstract.UserMapper<UiGithubUser> {

    override fun map(name: String, bio: String, imageUrl: String,isCollapsed: Boolean): UiGithubUser
        = UiGithubUser(name,bio,imageUrl,isCollapsed)

}