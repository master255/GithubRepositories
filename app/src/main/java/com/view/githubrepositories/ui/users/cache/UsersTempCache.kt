package com.view.githubrepositories.ui.users.cache

import com.view.githubrepositories.ui.core.cache.UiTempCache
import com.view.githubrepositories.ui.core.ItemsState
import com.view.githubrepositories.ui.core.adapter.GithubAdapter
import com.view.githubrepositories.ui.core.cache.StoreListTotalCache
import com.view.githubrepositories.ui.users.CollapseOrExpandState
import com.view.githubrepositories.ui.users.UiGithubUserState


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */

class UsersTempCache(
    itemsState: ItemsState<CollapseOrExpandState>,
    storeListTotalCache: StoreListTotalCache<UiGithubUserState>
) : UiTempCache.BaseUiGithubTotalCache<UiGithubUserState>(
    itemsState,
    storeListTotalCache
) {

    override fun updateAdapterByDefault(adapter: GithubAdapter<UiGithubUserState>?) {
        adapter?.update(UiGithubUserState.Empty.wrap())
    }

}