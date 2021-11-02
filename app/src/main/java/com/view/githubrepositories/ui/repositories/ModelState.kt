package com.view.githubrepositories.ui.repositories


/**
 * @author Master255 on 06.09.2021
 * masters@inbox.ru
 */
interface ModelState<T> {

    fun modelWithChangedState() : T
}