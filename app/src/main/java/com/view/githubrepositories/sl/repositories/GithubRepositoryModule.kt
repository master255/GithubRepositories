package com.view.githubrepositories.sl.repositories

import com.view.githubrepositories.data.repositories.DataGithubRepositoryMapper
import com.view.githubrepositories.data.repositories.GithubRepoRepository
import com.view.githubrepositories.data.repositories.cache.CacheGithubRepositoryMapper
import com.view.githubrepositories.data.repositories.cache.GithubRepositoryCacheDataSource
import com.view.githubrepositories.data.repositories.cloud.GithubRepositoryCloudDataSource
import com.view.githubrepositories.data.repositories.cloud.GithubRepositoryService
import com.view.githubrepositories.domain.repositories.DomainGithubRepositoryMapper
import com.view.githubrepositories.domain.repositories.GithubRepositoryInteractor
import com.view.githubrepositories.sl.core.BaseModule
import com.view.githubrepositories.sl.core.CoreModule
import com.view.githubrepositories.ui.core.BaseViewModel
import com.view.githubrepositories.ui.repositories.*
import com.view.githubrepositories.core.DisposableStore
import com.view.githubrepositories.data.repositories.download.DownloadRepoRepository
import com.view.githubrepositories.data.repositories.download.cloud.GithubDownloadRepoCloudDataSource
import com.view.githubrepositories.data.repositories.download.cloud.GithubDownloadRepoService
import com.view.githubrepositories.data.repositories.download.file.*
import com.view.githubrepositories.domain.core.DomainDownloadExceptionMapper
import com.view.githubrepositories.domain.repositories.download.DomainDownloadRepositoryMapper
import com.view.githubrepositories.ui.core.cache.SaveCache
import com.view.githubrepositories.ui.repositories.cache.Local
import com.view.githubrepositories.ui.repositories.download.DownloadRepositoryCommunication
import com.view.githubrepositories.ui.repositories.download.DownloadRepositoryExceptionMappersStore
import com.view.githubrepositories.ui.repositories.download.UiDownloadExceptionMapper
import com.view.githubrepositories.ui.repositories.download.UiDownloadRepositoryMapper
import io.reactivex.disposables.CompositeDisposable


/**
 * @author Master255 on 25.08.2021
 * masters@inbox.ru
 */
class GithubRepositoryModule(
    private val coreModule: CoreModule
) : BaseModule<BaseViewModel<UiGithubRepositoryState>> {

    override fun viewModel(): BaseViewModel<UiGithubRepositoryState> {
        val repoCommunication = GithubRepositoryCommunication()
        val repoDownloadCommunication = DownloadRepositoryCommunication()
        val disposableStore = DisposableStore.Base(CompositeDisposable())

        val repositoryMappersStore = RepositoryMappersStore.Base(
            UiGithubRepositoryMapper(),
            UiGithubRepositoryStateMapper(),
            UiDownloadRepositoryMapper(
                coreModule.resource,
                UiDownloadExceptionMapper.Base(coreModule.resource)
            ),
            coreModule.exceptionMapper
        )

        val interactor = GithubRepositoryInteractor.Base(
            GithubRepoRepository.Base(
                GithubRepositoryCacheDataSource.Base(
                    coreModule.githubDao
                ),
                GithubRepositoryCloudDataSource.Base(
                    coreModule.networkService(GithubRepositoryService::class.java)
                ),
                CacheGithubRepositoryMapper(),
                DataGithubRepositoryMapper(),
                coreModule.repositoryCachedState
            ),
            DomainGithubRepositoryMapper(),
            DownloadRepoRepository.Base(
                GithubDownloadRepoCloudDataSource.Base(
                    coreModule.networkService(GithubDownloadRepoService::class.java)
                ),
                File.ZipFile(
                    Folder.Base(
                        coreModule.fileWriter,
                        coreModule.cachedFile,
                        CheckFileByExist.Base()
                    )
                ),
                SizeFile.Base(),
                Kbs.Base()
            ),
            DomainDownloadRepositoryMapper(
                DomainDownloadExceptionMapper.Base()
            )
        )
        return GithubRepositoryViewModel.Base(
            Remote.Base(
                interactor,
                repoCommunication,
                repoDownloadCommunication,
                disposableStore,
                repositoryMappersStore,
                DownloadRepositoryExceptionMappersStore.Base(
                    DomainDownloadExceptionMapper.Base(),
                    UiDownloadExceptionMapper.Base(coreModule.resource)
                )
            ),
            Local.Base(interactor),
            repoCommunication,
            repoDownloadCommunication,
            disposableStore,
            SaveCache.Repository(coreModule.githubDao,com.view.githubrepositories.ui.repositories.CacheGithubRepositoryMapper.Base())
        )
    }
}