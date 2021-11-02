package com.view.githubrepositories.sl.users

import com.view.githubrepositories.core.DisposableStore
import com.view.githubrepositories.data.users.DataGithubUserMapper
import com.view.githubrepositories.data.users.GithubUserRepository
import com.view.githubrepositories.data.users.cache.CacheGithubUserMapper
import com.view.githubrepositories.data.users.cache.GithubUserCacheDataSource
import com.view.githubrepositories.data.users.cloud.GithubUserCloudDataSource
import com.view.githubrepositories.data.users.cloud.GithubUserService
import com.view.githubrepositories.domain.core.DomainDownloadExceptionMapper
import com.view.githubrepositories.domain.users.DomainGithubUserMapper
import com.view.githubrepositories.domain.users.GithubUserInteractor
import com.view.githubrepositories.sl.core.BaseModule
import com.view.githubrepositories.sl.core.CoreModule
import com.view.githubrepositories.ui.core.BaseViewModel
import com.view.githubrepositories.ui.core.cache.SaveCache
import com.view.githubrepositories.ui.users.*
import com.view.githubrepositories.ui.users.cache.Local
import io.reactivex.disposables.CompositeDisposable


/**
 * @author Master255 on 25.08.2021
 * masters@inbox.ru
 */
class GithubUserModule(
    private val coreModule: CoreModule
) : BaseModule<BaseViewModel<UiGithubUserState>> {

    override fun viewModel(): BaseViewModel<UiGithubUserState> {
        val githubUserInteractor = GithubUserInteractor.Base(
            GithubUserRepository.Base(
                GithubUserCloudDataSource.Base(
                    coreModule.networkService(GithubUserService::class.java)
                ),
                GithubUserCacheDataSource.Base(
                    coreModule.githubDao
                ),
                DataGithubUserMapper(coreModule.text),
                CacheGithubUserMapper(),
                coreModule.userCachedState),
            DomainGithubUserMapper(),
            DomainDownloadExceptionMapper.Base()
        )
        val communication = GithubUserCommunication()
        val disposableStore = DisposableStore.Base(CompositeDisposable())

        val userMappersStore = UserMappersStore.Base(
            UiGithubUserMapper(),
            UiGithubUserStateMapper(),
            coreModule.exceptionMapper
        )

        return GithubUserViewModel.Base(
            Remote(
                githubUserInteractor,
                communication,
                disposableStore,
                userMappersStore
            ),
            Local(
                githubUserInteractor,
                communication,
                disposableStore,
                coreModule.resource,
                userMappersStore
            ),
            disposableStore,
            communication,
            SaveCache.User(
                coreModule.githubDao,
                com.view.githubrepositories.ui.users.CacheGithubUserMapper.Base()
            )
        )
    }
}