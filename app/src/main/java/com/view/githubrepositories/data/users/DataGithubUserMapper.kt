package com.view.githubrepositories.data.users

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.data.core.Text

/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
class DataGithubUserMapper(
    private val text: Text
) : Abstract.UserMapper<DataGithubUser> {

    override fun map(name: String, bio: String, imageUrl: String,isCollapsed: Boolean): DataGithubUser
        =  DataGithubUser(text.subText(name),bio,imageUrl,isCollapsed)

}