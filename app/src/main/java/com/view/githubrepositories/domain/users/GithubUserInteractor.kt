package com.view.githubrepositories.domain.users

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.core.SaveState
import com.view.githubrepositories.data.users.DataGithubUser
import com.view.githubrepositories.data.users.GithubUserRepository
import com.view.githubrepositories.domain.core.GithubInteractor
import com.view.githubrepositories.domain.core.DomainDownloadExceptionMapper
import com.view.githubrepositories.ui.users.CollapseOrExpandState
import io.reactivex.Single


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
interface GithubUserInteractor : GithubInteractor<DomainGithubUser>,
    SaveState {

    fun users(): Single<List<DomainGithubUser>>


    class Base(
        private val githubUserRepository: GithubUserRepository<Single<DataGithubUser>,Single<List<DataGithubUser>>>,
        private val domainGithubUserMapper: Abstract.UserMapper<DomainGithubUser>,
        private val domainDownloadExceptionMapper: DomainDownloadExceptionMapper
    ) : GithubUserInteractor {

        override fun data(query: String): Single<DomainGithubUser> {
            return try {
                githubUserRepository.user(query).flatMap { dataGithubUser ->
                    Single.just(dataGithubUser.map(domainGithubUserMapper))
                }
            } catch (e: Exception) {
                throw domainDownloadExceptionMapper.map(e)
            }
        }

        override fun users(): Single<List<DomainGithubUser>> =
            githubUserRepository.users().flatMap { dataGithubUsers ->
                Single.just(dataGithubUsers.map { it.map(domainGithubUserMapper) })
            }


        override fun saveState(state: CollapseOrExpandState) =
            githubUserRepository.saveState(state)
    }
}