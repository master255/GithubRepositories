package com.view.githubrepositories.data.users.cache

import com.view.githubrepositories.core.Save
import com.view.githubrepositories.data.core.GithubDao
import com.view.githubrepositories.ui.users.CollapseOrExpandState
import io.reactivex.Single


/**
 * @author Master255 on 20.08.2021
 * masters@inbox.ru
 */
interface GithubUserCacheDataSource : Save<CacheGithubUser> {

    fun fetchUsers() : Single<List<CacheGithubUser>>

    fun usersByState(state: CollapseOrExpandState) : Single<List<CacheGithubUser>>

    fun commonUser(query: String) : CacheGithubUser?

    fun userByState(userName: String,state: CollapseOrExpandState) : Single<CacheGithubUser?>

    class Base(
        private val githubDao: GithubDao
    ) : GithubUserCacheDataSource {

        override fun fetchUsers(): Single<List<CacheGithubUser>>
            = githubDao.users()

        override fun usersByState(state: CollapseOrExpandState): Single<List<CacheGithubUser>>
            = githubDao.usersByState(state)

        override fun commonUser(query: String): CacheGithubUser?
            = githubDao.commonUser(query)

        override fun saveData(data: CacheGithubUser)
            = githubDao.insertUser(data)

        override fun userByState(userName: String,state: CollapseOrExpandState) : Single<CacheGithubUser?>
            = githubDao.userByState(userName,state)
    }
}