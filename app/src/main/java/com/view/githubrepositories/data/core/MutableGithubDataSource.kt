package com.view.githubrepositories.data.core

import com.view.githubrepositories.core.Save


/**
 * @author Master255 on 20.08.2021
 * masters@inbox.ru
 */
interface MutableGithubDataSource<T,D> : Save<D> {

    fun fetchData(param: String) : T

}