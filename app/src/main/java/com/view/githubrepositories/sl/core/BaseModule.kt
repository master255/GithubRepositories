package com.view.githubrepositories.sl.core

import androidx.lifecycle.ViewModel


/**
 * @author Master255 on 25.08.2021
 * masters@inbox.ru
 */
interface BaseModule<T : ViewModel> {

    fun viewModel() : T
}