package com.view.githubrepositories.ui.repositories

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.data.repositories.cache.CacheGithubRepository


/**
 * @author Master255 on 27.08.2021
 * masters@inbox.ru
 */
interface CacheGithubRepositoryMapper : Abstract.Mapper {

    fun map(isCollapsed: Boolean,uiGithubRepository: UiGithubRepository) : CacheGithubRepository

    class Base : CacheGithubRepositoryMapper {
        override fun map(isCollapsed: Boolean,uiGithubRepository: UiGithubRepository): CacheGithubRepository
            =  uiGithubRepository.mapTo(isCollapsed)
    }
}