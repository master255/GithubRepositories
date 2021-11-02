package com.view.githubrepositories.data.core

import android.content.Context


/**
 * @author Master255 on 21.08.2021
 * masters@inbox.ru
 */
interface WrapDatabase<T : Dao> {
    fun daoDatabase() : T

    class Base(private val context: Context) : WrapDatabase<GithubDao> {

        override fun daoDatabase(): GithubDao
            =  GithubAppDatabase.database(context).dao()
    }
}