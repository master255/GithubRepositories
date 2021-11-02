package com.view.githubrepositories.core

import com.view.githubrepositories.ui.users.CollapseOrExpandState


/**
 * @author Master255 on 29.08.2021
 * masters@inbox.ru
 */
interface SaveState {

    fun saveState(state: CollapseOrExpandState)
}