package com.view.githubrepositories.domain.users

import com.view.githubrepositories.core.Abstract


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
class DomainGithubUserMapper : Abstract.UserMapper<DomainGithubUser> {

    override fun map(name: String, bio: String, imageUrl: String,isCollapsed: Boolean): DomainGithubUser
        = DomainGithubUser(name,bio,imageUrl,isCollapsed)

}