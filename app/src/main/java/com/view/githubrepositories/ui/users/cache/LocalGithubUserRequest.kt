package com.view.githubrepositories.ui.users.cache

import com.view.githubrepositories.R
import com.view.githubrepositories.core.*
import com.view.githubrepositories.domain.users.GithubUserInteractor
import com.view.githubrepositories.ui.core.BaseGithubUserRequest
import com.view.githubrepositories.ui.users.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @author Master255 on 21.08.2021
 * masters@inbox.ru
 */
abstract class LocalGithubUserRequest(
    private val githubUserInteractor: GithubUserInteractor,
    private val communication: GithubUserCommunication,
    private val githubUserDisposableStore: DisposableStore,
    private val resource: Resource,
    private val userMappersStore: UserMappersStore
) : BaseGithubUserRequest(
    githubUserInteractor,
    communication,
    githubUserDisposableStore,
    userMappersStore
), SaveState {

     fun request() {
        communication.changeValue(UiGithubUserState.Progress.wrap())
        githubUserInteractor
            .users()
            .subscribeOn(Schedulers.io())
            .flatMap { domainGithubUsers ->
                Single.just( domainGithubUsers.map { it.map(userMappersStore.uiUserMapper()) } )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ uiGithubUsers ->
                uiGithubUsers?.let { users ->
                    if (users.isNotEmpty())
                        communication.changeValue(users.map { it.map(userMappersStore.uiUserStateMapper()) })
                    else
                        communication.changeValue(listOf(UiGithubUserState.Empty))
                }
            }, { error ->
                error?.let { throwable ->
                    communication.changeValue(UiGithubUserState.Fail(resource.string(R.string.local_error) + throwable.message).wrap())
                }
            }).addToDisposableStore(githubUserDisposableStore)
    }


    override fun saveState(state: CollapseOrExpandState)
        = githubUserInteractor.saveState(state)
}