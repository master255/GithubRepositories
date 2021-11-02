package com.view.githubrepositories.core

import com.view.githubrepositories.data.core.prefs.CachedState
import com.view.githubrepositories.data.repositories.cache.prefs.RepositoryCachedState
import com.view.githubrepositories.data.users.cache.prefs.UserCachedState
import java.lang.IllegalArgumentException


/**
 * @author Master255 on 19.08.2021
 * masters@inbox.ru
 */
class CachedStateFactory<T : CachedState>(
    private val userCachedState: UserCachedState,
    private val repositoryCachedState: RepositoryCachedState
) : Abstract.FactoryMapper<Class<T>, CachedState>  {

    override fun map(src: Class<T>): CachedState = when(src) {
        UserCachedState.Base::class.java -> userCachedState
        RepositoryCachedState.Base::class.java -> repositoryCachedState
        else -> throw IllegalArgumentException("CachedStateFactory not found class ${src::class.java}")
    }
}