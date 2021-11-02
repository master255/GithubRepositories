package com.view.githubrepositories.ui.core

import com.view.githubrepositories.R
import com.view.githubrepositories.ui.users.CollapseOrExpandState


/**
 * @author Master255 on 25.08.2021
 * masters@inbox.ru
 */

class MockBaseFragment: BaseFragment(R.layout.failure) {
    override fun searchByQuery(searchView: androidx.appcompat.widget.SearchView)
            = throw IllegalStateException("MockBaseFragment not use this method")

    override fun menuCollapsedState()
            = throw IllegalStateException("MockBaseFragment not use this method")

    override fun dataByState(state: CollapseOrExpandState)
            = throw IllegalStateException("MockBaseFragment not use this method")

    override fun previousFragment()
            = throw IllegalStateException("MockBaseFragment not use this method")
}