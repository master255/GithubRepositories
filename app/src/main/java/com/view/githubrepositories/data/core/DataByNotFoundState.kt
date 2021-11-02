package com.view.githubrepositories.data.core

import io.reactivex.Single


/**
 * @author Master255 on 30.08.2021
 * masters@inbox.ru
 */
interface DataByNotFoundState<T> {

    fun dataByNotFoundState() : Single<List<T>>
}