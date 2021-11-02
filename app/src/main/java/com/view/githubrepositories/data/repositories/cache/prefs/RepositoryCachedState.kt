package com.view.githubrepositories.data.repositories.cache.prefs

import android.content.Context
import com.view.githubrepositories.data.core.prefs.AbstractCachedState
import com.view.githubrepositories.data.core.prefs.CachedState
import com.view.githubrepositories.data.core.prefs.CollapseOrExpandStateFactory
import com.view.githubrepositories.data.core.prefs.SavedValueStateFactory
import com.view.githubrepositories.data.repositories.cache.CacheGithubRepository
import com.view.githubrepositories.data.repositories.cache.GithubRepositoryCacheDataSource
import com.view.githubrepositories.ui.users.CollapseOrExpandState
import io.reactivex.Single


/**
 * @author Master255 on 29.08.2021
 * masters@inbox.ru
 */

interface RepositoryCachedState : CachedState {

    fun repositories(owner: String,cacheDataSource: GithubRepositoryCacheDataSource) : Single<List<CacheGithubRepository>>

    fun repository(owner: String,repo: String,cacheDataSource: GithubRepositoryCacheDataSource) : Single<CacheGithubRepository?>

    class Base(
        context: Context,
        private val savedValueStateFactory: SavedValueStateFactory,
        private val collapseOrExpandStateFactory: CollapseOrExpandStateFactory
    ) : RepositoryCachedState, AbstractCachedState() {

        private val statePreferences = context.getSharedPreferences(REPOSITORY_PREFERENCES_NAME,Context.MODE_PRIVATE)

        override fun repositories(
            owner: String,
            cacheDataSource: GithubRepositoryCacheDataSource
        ): Single<List<CacheGithubRepository>>
            = cacheDataSource.repositoriesByState(owner,state())

        override fun repository(
            owner: String,
            repo: String,
            cacheDataSource: GithubRepositoryCacheDataSource
        ): Single<CacheGithubRepository?>
            = cacheDataSource.fetchRepository(owner,repo,state())

        override fun saveState(state: CollapseOrExpandState) {
            val savedValue = savedValueStateFactory.map(state)
            statePreferences.edit().putString(KEY_STATE, savedValue).apply()
        }

        private fun state() : CollapseOrExpandState {
            val savedValue = requireNotNull(statePreferences.getString(KEY_STATE, DEFAULT))
            return collapseOrExpandStateFactory.map(savedValue)
        }

        override fun currentState(): CollapseOrExpandState {
            val currentState = requireNotNull(statePreferences.getString(KEY_STATE, DEFAULT))
            return collapseOrExpandStateFactory.map(currentState)
        }

        private companion object {
            const val REPOSITORY_PREFERENCES_NAME = "repos_state"
        }
    }
}