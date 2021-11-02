package com.view.githubrepositories.ui.users

import com.view.githubrepositories.core.Abstract


/**
 * @author Master255 on 21.08.2021
 * masters@inbox.ru
 */
class UiGithubUserStateMapper : Abstract.UserMapper<UiGithubUserState> {

    override fun map(name: String, bio: String, imageUrl: String,isCollapsed: Boolean): UiGithubUserState
        = UiGithubUserState.Base(
            UiGithubUser(name,bio,imageUrl,isCollapsed),
            isCollapsed)
}