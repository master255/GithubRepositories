package com.view.githubrepositories.ui.core

import com.view.githubrepositories.ui.repositories.TempGithubUserName
import com.view.githubrepositories.ui.users.CollapseOrExpandState


/**
 * @author Master255 on 30.08.2021
 * masters@inbox.ru
 */

interface TitleToolbar {

    fun title() : String

    fun title(name: TempGithubUserName) : String

    class Base(
        private val itemsState: ItemsState<CollapseOrExpandState>
    ) : TitleToolbar {

        override fun title(): String
            = "(${itemsState.currentState().asString()})"

        override fun title(name: TempGithubUserName)
            = name.currentName() + title()
    }
}