package com.view.githubrepositories.ui.core


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.view.githubrepositories.core.DisposableStore
import com.view.githubrepositories.core.SaveState
import com.view.githubrepositories.ui.core.cache.SaveCache
import com.view.githubrepositories.ui.users.GithubUserRequest


/**
 * @author Master255 on 23.08.2021
 * masters@inbox.ru
 */
abstract class BaseViewModel<T : CommunicationModel.ItemCommunicationModel> (
    private val communication: Communication.Base<T>,
    private val disposableStore: DisposableStore,
    private val githubUserRequest: GithubUserRequest<String>,
    private val saveCache: SaveCache<T>
) : ViewModel(),
    com.view.githubrepositories.ui.core.ViewModel<T>,
    GithubUserRequest<String>,
    SaveState {

    override fun observe(owner: LifecycleOwner, observer: Observer<List<T>>)
        = communication.observe(owner, observer)

    override fun onCleared() {
        disposableStore.dispose()
        super.onCleared()
    }

    override fun data(param: String)
        = githubUserRequest.data(param)

    override fun saveData(data: List<T>)
        = saveCache.saveData(data)

}