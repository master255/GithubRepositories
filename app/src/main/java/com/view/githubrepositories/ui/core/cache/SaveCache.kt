package com.view.githubrepositories.ui.core.cache

import com.view.githubrepositories.data.core.GithubDao
import com.view.githubrepositories.core.Save
import com.view.githubrepositories.ui.core.CommunicationModel
import com.view.githubrepositories.ui.repositories.CacheGithubRepositoryMapper
import com.view.githubrepositories.ui.repositories.UiGithubRepositoryState
import com.view.githubrepositories.ui.users.CacheGithubUserMapper
import com.view.githubrepositories.ui.users.UiGithubUserState


/**
 * @author Master255 on 27.08.2021
 * masters@inbox.ru
 */
interface SaveCache<T : CommunicationModel.ItemCommunicationModel> : Save<List<T>> {

    class User(
        private val dao: GithubDao,
        private val mapper: CacheGithubUserMapper
    ) : SaveCache<UiGithubUserState> {

        override fun saveData(data: List<UiGithubUserState>) {
            val cacheGithubUsers = data.map { it.map(mapper) }
            cacheGithubUsers.map {
                dao.insertUser(it)
            }
        }
    }

    class Repository(
        private val dao: GithubDao,
        private val mapper: CacheGithubRepositoryMapper
    ): SaveCache<UiGithubRepositoryState> {

        override fun saveData(data: List<UiGithubRepositoryState>) {
            val cacheGithubRepositories = data.map { it.map(mapper) }
            cacheGithubRepositories.map {
                dao.insertRepository(it)
            }
        }
    }

}