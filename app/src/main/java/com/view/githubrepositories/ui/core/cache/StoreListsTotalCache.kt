package com.view.githubrepositories.ui.core.cache

import com.view.githubrepositories.ui.core.CommunicationModel
import com.view.githubrepositories.ui.core.adapter.GithubAdapter
import com.view.githubrepositories.ui.core.cache.list.*
import com.view.githubrepositories.ui.users.CollapseOrExpandState


/**
 * @author Master255 on 30.08.2021
 * masters@inbox.ru
 */

interface StoreListTotalCache<T : CommunicationModel.ItemCommunicationModel>  {

    fun updateLists(items: List<T>)

    fun updateLists(item: T)

    fun updateAdapterByState(adapter: GithubAdapter<T>?, state: CollapseOrExpandState)

    fun listByState(state: CollapseOrExpandState) : MutableList<T>

    fun isEmptyTotalCache(state: CollapseOrExpandState) : Boolean

    class Base<T : CommunicationModel.ItemCommunicationModel>(
        private val listAnyStateTotalCache: MutableList<T>,
        private val listCollapsedTotalCache: MutableList<T>,
        private val listExpandedTotalCache: MutableList<T>,
        private val storeFilters: StoreFilters<T>,
        private val containsElement: ContainsItem<T>,
        private val listOperation: ListOperation<T>,
        private val wasReplaced: (T) -> Unit
    ) : StoreListTotalCache<T> {

        override fun updateLists(items: List<T>) {
            val baseItems = storeFilters.filterByBase(items)

            if (containsElement.notContains(listAnyStateTotalCache,baseItems)) {
                listAnyStateTotalCache.addAll(baseItems)
            }

            val collapsedItems = storeFilters.filterByCollapsed(baseItems)
            listOperation.addUniqueItems(listCollapsedTotalCache,collapsedItems)

            val expandedItems = storeFilters.filterByExpanded(baseItems)
            listOperation.addUniqueItems(listExpandedTotalCache,expandedItems)

        }

        override fun updateLists(item: T) {

            if (item.isCollapsed()) {
                listOperation.removeItemByState(listExpandedTotalCache,item)
                listOperation.add(listCollapsedTotalCache,item)
            } else {
                listOperation.removeItemByState(listCollapsedTotalCache,item)
                listOperation.add(listExpandedTotalCache,item)
            }

            listOperation.replace(listAnyStateTotalCache,item,wasReplaced)
        }

        override fun updateAdapterByState(adapter: GithubAdapter<T>?, state: CollapseOrExpandState) {
            adapter?.update(listByState(state))
        }

        override fun listByState(state: CollapseOrExpandState): MutableList<T> = when (state) {
            is CollapseOrExpandState.Expanded -> listExpandedTotalCache
            is CollapseOrExpandState.Collapsed -> listCollapsedTotalCache
            else -> listAnyStateTotalCache

        }

        override fun isEmptyTotalCache(state: CollapseOrExpandState) = when (state) {
            is CollapseOrExpandState.Expanded -> listExpandedTotalCache.isEmpty()
            is CollapseOrExpandState.Collapsed -> listCollapsedTotalCache.isEmpty()
            else -> listAnyStateTotalCache.isEmpty()
        }

    }

}
