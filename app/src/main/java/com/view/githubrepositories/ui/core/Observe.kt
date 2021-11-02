package com.view.githubrepositories.ui.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer


/**
 * @author Master255 on 23.08.2021
 * masters@inbox.ru
 */
interface Observe<T : CommunicationModel> {

    fun observe(owner: LifecycleOwner,observer: Observer<List<T>>)
}