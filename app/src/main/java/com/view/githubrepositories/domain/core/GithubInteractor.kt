package com.view.githubrepositories.domain.core

import io.reactivex.Single


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
interface GithubInteractor<T> {

    fun data(query: String) : Single<T>
}