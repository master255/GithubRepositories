package com.view.githubrepositories.ui.users

import com.view.githubrepositories.core.DisposableStore
import com.view.githubrepositories.core.SaveState
import com.view.githubrepositories.ui.core.*
import com.view.githubrepositories.ui.core.cache.SaveCache
import com.view.githubrepositories.ui.users.cache.LocalGithubUserRequest


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
interface GithubUserViewModel<T : CommunicationModel> : ViewModel<T>, SaveState {

    fun user(query: String)

    fun users()

    class Base(
        githubUserRemoteRequest: BaseGithubUserRequest,
        private val githubUserLocalRequest: LocalGithubUserRequest,
        githubUserDisposableStore: DisposableStore,
        communication: GithubUserCommunication,
        saveCache: SaveCache<UiGithubUserState>,
    ) : BaseViewModel<UiGithubUserState>(
        communication,
        githubUserDisposableStore,
        githubUserRemoteRequest,
        saveCache
    ), GithubUserViewModel<UiGithubUserState> {

        override fun user(query: String)
            = githubUserLocalRequest.data(query)

        override fun users()
            = githubUserLocalRequest.request()

        override fun saveState(state: CollapseOrExpandState)
            = githubUserLocalRequest.saveState(state)
    }

}