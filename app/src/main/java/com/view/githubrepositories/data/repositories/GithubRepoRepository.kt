package com.view.githubrepositories.data.repositories

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.core.SaveState
import com.view.githubrepositories.data.core.DataByNotFoundState
import com.view.githubrepositories.data.repositories.cache.CacheGithubRepositoryMapper
import com.view.githubrepositories.data.repositories.cache.GithubRepositoryCacheDataSource
import com.view.githubrepositories.data.repositories.cache.prefs.RepositoryCachedState
import com.view.githubrepositories.data.repositories.cloud.CloudGithubRepository
import com.view.githubrepositories.data.repositories.cloud.GithubRepositoryCloudDataSource
import com.view.githubrepositories.ui.users.CollapseOrExpandState
import io.reactivex.Single
import java.lang.IllegalStateException


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */


interface GithubRepoRepository<T,E> : SaveState,
    DataByNotFoundState<DataGithubRepository> {

    fun repository(userName: String, repo: String): T


    fun repositories(userName: String): E

    class Base(
        private val githubRepositoryCacheDataSource: GithubRepositoryCacheDataSource,
        private val githubRepositoryCloudDataSource: GithubRepositoryCloudDataSource<Single<CloudGithubRepository>,Single<List<CloudGithubRepository>>>,
        private val cacheGithubRepositoryMapper: CacheGithubRepositoryMapper,
        private val dataGithubRepositoryMapper: Abstract.RepositoryMapper<DataGithubRepository>,
        private val repositoryCachedState: RepositoryCachedState
    ) : GithubRepoRepository<Single<DataGithubRepository>,Single<List<DataGithubRepository>>> {

        override fun repository(owner: String, repo: String): Single<DataGithubRepository> {
            val commonRepository = githubRepositoryCacheDataSource.commonRepository(owner,repo)
            return repositoryCachedState.repository(owner, repo,githubRepositoryCacheDataSource)
                .flatMap { cacheGithubRepository ->
                    Single.just(cacheGithubRepository.map(dataGithubRepositoryMapper))
                }.onErrorResumeNext {
                    if (commonRepository != null) {
                        dataByNotFoundState()
                    }
                    val cloudGithubRepository =
                        githubRepositoryCloudDataSource.repository(owner, repo)
                    cloudGithubRepository.flatMap { cloudRepo ->
                        val cacheRepo = cloudRepo.map(cacheGithubRepositoryMapper,owner)
                        githubRepositoryCacheDataSource.saveData(cacheRepo)
                        Single.just(cloudRepo.map(dataGithubRepositoryMapper,owner))
                }
            }
        }

        override fun repositories(
            owner: String
        ): Single<List<DataGithubRepository>> {
            val allCachedRepositories = githubRepositoryCacheDataSource.commonListRepository(owner)
            return repositoryCachedState.repositories(owner,githubRepositoryCacheDataSource)
                .flatMap { cacheGithubRepositories ->
                    if (cacheGithubRepositories.isEmpty()) {
                        if (allCachedRepositories.isEmpty()) {
                            cloudRepositoriesByOwner(owner)
                        } else {
                            dataByNotFoundState()
                        }
                    } else {
                        val dataGithubRepositories =
                            cacheGithubRepositories.map { it.map(dataGithubRepositoryMapper) }
                        Single.just(dataGithubRepositories)
                    }
                }
        }

        private fun cloudRepositoriesByOwner(owner: String) : Single<List<DataGithubRepository>> {
            val cloudGithubRepositories =
                githubRepositoryCloudDataSource.fetchData(owner)
            return cloudGithubRepositories.flatMap { cloudRepos ->
                val cacheRepos = cloudRepos.map {
                        cloudRepo -> cloudRepo.map(cacheGithubRepositoryMapper,owner)
                }
                githubRepositoryCacheDataSource.saveListData(cacheRepos)
                Single.just(cloudRepos.map { it.map(dataGithubRepositoryMapper,owner) })
            }
        }

        override fun dataByNotFoundState(): Single<List<DataGithubRepository>>
            = throw DataByStateNotFoundException()

        override fun saveState(state: CollapseOrExpandState)
            = repositoryCachedState.saveState(state)
    }

    class Test(
        private val testCloudDataSource: GithubRepositoryCloudDataSource<GithubRepositoryCloudDataSource.Test.TestCloudRepository,List<GithubRepositoryCloudDataSource.Test.TestCloudRepository>>
    ) : GithubRepoRepository<GithubRepositoryCloudDataSource.Test.TestCloudRepository,List<GithubRepositoryCloudDataSource.Test.TestCloudRepository>> {

        override fun repository(userName: String, repo: String): GithubRepositoryCloudDataSource.Test.TestCloudRepository
            = testCloudDataSource.repository(userName,"Java")

        override fun repositories(userName: String): List<GithubRepositoryCloudDataSource.Test.TestCloudRepository>
            = testCloudDataSource.fetchData(userName)

        override fun saveState(state: CollapseOrExpandState)
            = throw IllegalStateException("TestCloudRepository not use saveState()")

        override fun dataByNotFoundState(): Single<List<DataGithubRepository>>
            = throw IllegalStateException("TestCloudRepository not use dataByNotFoundState()")

    }
}