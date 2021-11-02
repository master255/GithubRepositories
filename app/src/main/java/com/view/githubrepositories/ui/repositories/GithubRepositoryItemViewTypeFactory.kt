package com.view.githubrepositories.ui.repositories

import com.view.githubrepositories.core.Abstract


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */

interface GithubRepositoryItemViewTypeFactory : Abstract.FactoryMapper<UiGithubRepositoryState, Int> {

    class Base : GithubRepositoryItemViewTypeFactory {

        override fun map(src: UiGithubRepositoryState): Int = when(src) {
            is UiGithubRepositoryState.Progress -> 1
            is UiGithubRepositoryState.Base -> 2
            is UiGithubRepositoryState.Empty -> 3
            else -> 4
        }
    }
}

