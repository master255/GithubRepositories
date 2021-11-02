package com.view.githubrepositories.ui.repositories.cache

import com.view.githubrepositories.core.*
import com.view.githubrepositories.domain.repositories.GithubRepositoryInteractor
import com.view.githubrepositories.ui.users.CollapseOrExpandState

interface Local : SaveState {

    class Base(
        private val githubRepositoryInteractor: GithubRepositoryInteractor
    ) : Local {

        override fun saveState(state: CollapseOrExpandState)
            = githubRepositoryInteractor.saveState(state)
    }
}