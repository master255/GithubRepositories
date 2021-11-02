package com.view.githubrepositories.ui.core


/**
 * @author Master255 on 21.08.2021
 * masters@inbox.ru
 */

interface ListWrapper<T> {

    fun wrap() : List<T>

    class TestModel : ListWrapper<TestModel> {

        override fun wrap(): List<TestModel>
            = listOf(this)
    }
}