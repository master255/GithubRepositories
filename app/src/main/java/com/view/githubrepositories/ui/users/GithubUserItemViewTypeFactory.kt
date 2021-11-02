package com.view.githubrepositories.ui.users

import com.view.githubrepositories.core.Abstract


/**
 * @author Master255 on 19.08.2021
 * masters@inbox.ru
 */

interface GithubUserItemViewTypeFactory : Abstract.FactoryMapper<UiGithubUserState, Int> {

    class Base : GithubUserItemViewTypeFactory {

        override fun map(src: UiGithubUserState): Int = when(src) {
            is UiGithubUserState.Progress -> 1
            is UiGithubUserState.Base -> 2
            is UiGithubUserState.Empty -> 3
            else -> 4
        }
    }
}
