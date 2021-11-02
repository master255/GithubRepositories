package com.view.githubrepositories.data.core.prefs

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.core.Resource
import com.view.githubrepositories.ui.users.CollapseOrExpandState


/**
 * @author Master255 on 29.08.2021
 * masters@inbox.ru
 */

interface CollapseOrExpandStateFactory : Abstract.FactoryMapper<String,CollapseOrExpandState> {

    class Base(
        private val resource: Resource
    ) : CollapseOrExpandStateFactory,
        com.view.githubrepositories.data.core.prefs.AbstractStateFactory() {

        override fun map(src: String): CollapseOrExpandState = when(src) {
            COLLAPSED -> CollapseOrExpandState.Collapsed(resource)
            EXPANDED -> CollapseOrExpandState.Expanded(resource)
            else -> CollapseOrExpandState.Any(resource)
        }

    }
}

