package com.view.githubrepositories.data.repositories.cache

import com.view.githubrepositories.data.core.GithubDao
import com.view.githubrepositories.ui.users.CollapseOrExpandState
import io.reactivex.Single


/**
 * @author Master255 on 23.08.2021
 * masters@inbox.ru
 */

interface  GithubRepositoryCacheDataSource
    : SaveList<CacheGithubRepository> {

    fun fetchRepository(param: String,repo: String,state: CollapseOrExpandState): Single<CacheGithubRepository?>

    fun commonRepository(owner: String,repo: String) : CacheGithubRepository?

    fun repositoriesByState(owner: String,state: CollapseOrExpandState) : Single<List<CacheGithubRepository>>

    fun commonListRepository(owner: String) : List<CacheGithubRepository>

    class Base (
        private val githubDao: GithubDao
    ) : GithubRepositoryCacheDataSource {

        override fun fetchRepository(param: String,repo: String,state: CollapseOrExpandState): Single<CacheGithubRepository?>
            = githubDao.repository(param,repo,state)

        override fun commonRepository(owner: String, repo: String): CacheGithubRepository?
            = githubDao.commonRepository(owner,repo)

        override fun repositoriesByState(owner: String, state: CollapseOrExpandState): Single<List<CacheGithubRepository>>
            = githubDao.repositoriesByState(owner,state)

        override fun commonListRepository(owner: String): List<CacheGithubRepository>
            = githubDao.listRepository(owner)

        override fun saveListData(listData: List<CacheGithubRepository>)
            = githubDao.insertRepositories(listData)

        override fun saveData(data: CacheGithubRepository)
            = githubDao.insertRepository(data)
    }
}