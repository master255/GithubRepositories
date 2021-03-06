package com.view.githubrepositories.ui.repositories

import com.view.githubrepositories.domain.repositories.GithubRepositoryInteractor
import com.view.githubrepositories.ui.core.CleanDisposable
import com.view.githubrepositories.core.DisposableStore
import com.view.githubrepositories.ui.repositories.download.DownloadRepositoryCommunication
import com.view.githubrepositories.ui.repositories.download.DownloadRepositoryExceptionMappersStore
import com.view.githubrepositories.ui.repositories.download.UiGithubDownloadFileState
import com.view.githubrepositories.ui.users.GithubUserRequest
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * @author Master255 on 21.08.2021
 * masters@inbox.ru
 */
interface Remote : GithubUserRequest<String> {

    fun repository(owner: String, repo: String)

    fun downloadRepository(owner: String,repo: String)

    class Base(
        private val githubRepositoryInteractor: GithubRepositoryInteractor,
        private val githubRepoCommunication: GithubRepositoryCommunication,
        private val downloadGithubRepoCommunication: DownloadRepositoryCommunication,
        private val githubRepositoryDisposableStore: DisposableStore,
        private val repositoryMappersStore: RepositoryMappersStore,
        private val downloadRepositoryExceptionMappersStore: DownloadRepositoryExceptionMappersStore
        ) : Remote, CleanDisposable {

        override fun data(owner: String) {
            githubRepoCommunication.changeValue(UiGithubRepositoryState.Progress.wrap())
            githubRepositoryInteractor
                .data(owner)
                .subscribeOn(Schedulers.io())
                .flatMap { domainGithubRepositories ->
                    Single.just(domainGithubRepositories.map { it.map(repositoryMappersStore.uiRepositoryMapper()) })
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { uiGithubRepositories ->
                    if (uiGithubRepositories.isEmpty())
                        githubRepoCommunication.changeValue(UiGithubRepositoryState.Empty.wrap())
                    else
                        githubRepoCommunication.changeValue(uiGithubRepositories.map { it.map(repositoryMappersStore.uiRepositoryStateMapper()) })
                }, { error->
                    error?.let { throwable ->
                        val messageError = repositoryMappersStore.uiGithubExceptionMapper().map(throwable)
                        githubRepoCommunication.changeValue(UiGithubRepositoryState.Fail(messageError).wrap())
                    }
                }).addToDisposableStore(githubRepositoryDisposableStore)
        }

        override fun repository(owner: String, repo: String) {
            githubRepoCommunication.changeValue(UiGithubRepositoryState.Progress.wrap())
            githubRepositoryInteractor
                .repository(owner, repo)
                .subscribeOn(Schedulers.io())
                .flatMap { domainGithubRepository ->
                    Single.just(domainGithubRepository.map(repositoryMappersStore.uiRepositoryMapper()))
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { uiGithubRepository ->
                    githubRepoCommunication.changeValue(uiGithubRepository.map(repositoryMappersStore.uiRepositoryStateMapper()).wrap())
                }, { error->
                    error?.let { throwable ->
                        val messageError = repositoryMappersStore.uiGithubExceptionMapper().map(throwable)
                        githubRepoCommunication.changeValue(UiGithubRepositoryState.Fail(messageError).wrap())
                    }
                }).addToDisposableStore(githubRepositoryDisposableStore)
        }

        override fun downloadRepository(owner: String, repo: String) {
            downloadGithubRepoCommunication.changeValue(UiGithubDownloadFileState.Progress.wrap())
            githubRepositoryInteractor
                .downloadRepository(owner, repo)
                .subscribeOn(Schedulers.io())
                .flatMap { domainDownloadRepository ->
                    Single.just(domainDownloadRepository.map(repositoryMappersStore.uiDownloadRepositoryMapper()))
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { uiDownloadRepository ->
                    downloadGithubRepoCommunication.changeValue(uiDownloadRepository.mapTo(Unit).wrap())
                }, { error->
                    error?.let { throwable ->
                        val uiException = downloadRepositoryExceptionMappersStore.domainDownloadExceptionMapper().map(throwable)
                        val errorMessage = downloadRepositoryExceptionMappersStore.uiDownloadExceptionMapper().map(uiException)

                        downloadGithubRepoCommunication.changeValue(UiGithubDownloadFileState.Failure(errorMessage).wrap())
                    }
                }).addToDisposableStore(githubRepositoryDisposableStore)
        }

        override fun Disposable.addToDisposableStore(store: DisposableStore)
            = githubRepositoryDisposableStore.add(this)
    }
}