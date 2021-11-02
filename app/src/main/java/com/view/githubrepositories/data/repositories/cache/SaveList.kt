package com.view.githubrepositories.data.repositories.cache

import com.view.githubrepositories.core.Save


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
interface SaveList<T> : Save<T> {

    fun saveListData(listData: List<T>)
}