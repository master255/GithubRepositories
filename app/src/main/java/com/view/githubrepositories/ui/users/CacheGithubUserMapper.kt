package com.view.githubrepositories.ui.users

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.data.users.cache.CacheGithubUser


/**
 * @author Master255 on 27.08.2021
 * masters@inbox.ru
 */
interface CacheGithubUserMapper : Abstract.Mapper {

    fun map(isCollapsed: Boolean,uiGithubUser: UiGithubUser) : CacheGithubUser

    class Base : CacheGithubUserMapper {
        override fun map(isCollapsed: Boolean,uiGithubUser: UiGithubUser): CacheGithubUser
            =  uiGithubUser.mapTo(isCollapsed)
    }
}