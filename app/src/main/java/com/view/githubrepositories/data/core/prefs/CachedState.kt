package com.view.githubrepositories.data.core.prefs

import com.view.githubrepositories.core.SaveState
import com.view.githubrepositories.ui.users.CollapseOrExpandState


/**
 * @author Master255 on 29.08.2021
 * masters@inbox.ru
 */
interface CachedState : SaveState {

    fun currentState() : CollapseOrExpandState
}