package com.view.githubrepositories.ui.core.cache

import com.view.githubrepositories.ui.core.CommunicationModel
import com.view.githubrepositories.ui.core.ItemsState
import com.view.githubrepositories.ui.core.adapter.GithubAdapter
import com.view.githubrepositories.ui.repositories.UiGithubRepositoryState
import com.view.githubrepositories.ui.users.CollapseOrExpandState


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */

interface UiTempCache<T : CommunicationModel.ItemCommunicationModel> {

    fun add(items: List<T>)

    fun add(item: T)

    fun addAdapter(adapter: GithubAdapter<T>)

    fun updateAdapter()

    fun updateCurrentItemsState(state: CollapseOrExpandState)

    abstract class BaseUiGithubTotalCache<T : CommunicationModel.ItemCommunicationModel>(
        private val itemsState: ItemsState<CollapseOrExpandState>,
        private val storeListTotalCache: StoreListTotalCache<T>
    ) : UiTempCache<T> {

        private var adapter: GithubAdapter<T>? = null

        abstract fun updateAdapterByDefault(adapter: GithubAdapter<T>?)

        override fun add(items: List<T>) = storeListTotalCache.updateLists(items)

        override fun add(item: T) = storeListTotalCache.updateLists(item)

        override fun addAdapter(adapter: GithubAdapter<T>) {
            this.adapter = adapter
        }

        override fun updateAdapter() {
            if (isEmptyTotalCache()) {
                updateAdapterByDefault(adapter)
            } else {
                storeListTotalCache.updateAdapterByState(adapter,itemsState.currentState())
            }
        }

        override fun updateCurrentItemsState(state: CollapseOrExpandState)
            = itemsState.updateState(state)

        private fun repositoryStateEmpty() : List<UiGithubRepositoryState.Empty>
            = listOf(UiGithubRepositoryState.Empty)

        private fun userStateEmpty() : List<UiGithubRepositoryState.Empty>
            = listOf(UiGithubRepositoryState.Empty)

        private fun isEmptyTotalCache()
            = storeListTotalCache.isEmptyTotalCache(itemsState.currentState())
    }
}