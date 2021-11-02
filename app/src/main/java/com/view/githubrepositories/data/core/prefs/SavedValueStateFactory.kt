package com.view.githubrepositories.data.core.prefs

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.ui.users.CollapseOrExpandState


/**
 * @author Master255 on 29.08.2021
 * masters@inbox.ru
 */

interface SavedValueStateFactory : Abstract.FactoryMapper<CollapseOrExpandState,String> {

    class Base : SavedValueStateFactory,
        com.view.githubrepositories.data.core.prefs.AbstractStateFactory() {

        override fun map(src: CollapseOrExpandState): String = when(src) {
            is CollapseOrExpandState.Collapsed -> COLLAPSED
            is CollapseOrExpandState.Expanded -> EXPANDED
            else -> ANY
        }
    }
}

