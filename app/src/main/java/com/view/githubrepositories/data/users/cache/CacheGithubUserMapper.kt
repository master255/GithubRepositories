package com.view.githubrepositories.data.users.cache

import com.view.githubrepositories.core.Abstract


/**
 * @author Master255 on 20.08.2021
 * masters@inbox.ru
 */
class CacheGithubUserMapper : Abstract.UserMapper<CacheGithubUser> {

    override fun map(name: String, bio: String, imageUrl: String,isCollapsed: Boolean): CacheGithubUser
        = CacheGithubUser(name,bio,imageUrl,isCollapsed)
}