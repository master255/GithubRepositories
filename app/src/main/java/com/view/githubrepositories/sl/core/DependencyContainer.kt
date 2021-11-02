package com.view.githubrepositories.sl.core

import com.view.githubrepositories.sl.repositories.GithubRepositoryModule
import com.view.githubrepositories.sl.repositories.download.GithubDownloadRepositoryModule
import com.view.githubrepositories.sl.users.GithubUserModule
import java.lang.IllegalArgumentException


/**
 * @author Master255 on 25.08.2021
 * masters@inbox.ru
 */
interface DependencyContainer {

    fun module(feature: Feature) : BaseModule<*>

    class Base(
        private val coreModule: CoreModule
    ) : DependencyContainer {

        override fun module(feature: Feature): BaseModule<*> = when(feature) {
                is Feature.User -> GithubUserModule(coreModule)
                is Feature.Repository -> GithubRepositoryModule(coreModule)
                is Feature.WriteFile -> GithubDownloadRepositoryModule(coreModule)
                else -> throw IllegalArgumentException("not feature $feature")
            }
        }
    }