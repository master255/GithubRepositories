package com.view.githubrepositories.ui.repositories.cache

import com.view.githubrepositories.ui.core.cache.UiTempCache
import com.view.githubrepositories.ui.core.ItemsState
import com.view.githubrepositories.ui.core.adapter.GithubAdapter
import com.view.githubrepositories.ui.core.cache.StoreListTotalCache
import com.view.githubrepositories.ui.repositories.UiGithubRepositoryState
import com.view.githubrepositories.ui.users.CollapseOrExpandState


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */

class RepositoriesTempCache(
    itemsState: ItemsState<CollapseOrExpandState>,
    storeListTotalCache: StoreListTotalCache<UiGithubRepositoryState>
) : UiTempCache.BaseUiGithubTotalCache<UiGithubRepositoryState>(
    itemsState,
    storeListTotalCache,
) {
    override fun updateAdapterByDefault(adapter: GithubAdapter<UiGithubRepositoryState>?) {
        adapter?.update(UiGithubRepositoryState.Empty.wrap())
    }
}