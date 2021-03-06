package com.view.githubrepositories.domain.repositories

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.core.SaveState
import com.view.githubrepositories.data.repositories.DataGithubRepository
import com.view.githubrepositories.data.repositories.GithubRepoRepository
import com.view.githubrepositories.data.repositories.download.DataDownloadFile
import com.view.githubrepositories.data.repositories.download.DownloadRepoRepository
import com.view.githubrepositories.domain.core.GithubInteractor
import com.view.githubrepositories.domain.repositories.download.DomainDownloadFile
import com.view.githubrepositories.domain.repositories.download.DomainDownloadRepositoryMapper
import com.view.githubrepositories.ui.users.CollapseOrExpandState
import io.reactivex.Single


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
interface GithubRepositoryInteractor : GithubInteractor<List<DomainGithubRepository>>,
    SaveState {

    fun repository(owner: String, repo: String) : Single<DomainGithubRepository>

    fun downloadRepository(owner: String, repo: String) : Single<DomainDownloadFile>

    class Base(
        private val githubRepositoryRepository: GithubRepoRepository<Single<DataGithubRepository>, Single<List<DataGithubRepository>>>,
        private val domainGithubRepositoryMapper: Abstract.RepositoryMapper<DomainGithubRepository>,
        private val downloadRepoRepository: DownloadRepoRepository<Single<DataDownloadFile>>,
        private val domainDownloadRepositoryMapper: DomainDownloadRepositoryMapper
    ) : GithubRepositoryInteractor {

        override fun data(owner: String): Single<List<DomainGithubRepository>>
            = githubRepositoryRepository.repositories(owner).flatMap { dataGithubRepositories ->
                Single.just(dataGithubRepositories.map { it.map(domainGithubRepositoryMapper) })
            }

        override fun repository(owner: String, repo: String) : Single<DomainGithubRepository>
            = githubRepositoryRepository.repository(owner, repo).flatMap { dataGithubRepository ->
                Single.just(dataGithubRepository.map(domainGithubRepositoryMapper))
        }

        override fun downloadRepository(
            owner: String,
            repo: String
        ): Single<DomainDownloadFile>
            = downloadRepoRepository.download(owner,repo).flatMap { dataDownloadRepo ->
                Single.just(dataDownloadRepo.map(domainDownloadRepositoryMapper))
        }

        override fun saveState(state: CollapseOrExpandState)
            = githubRepositoryRepository.saveState(state)
    }
}