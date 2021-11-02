package com.view.githubrepositories.ui.core

import com.view.githubrepositories.core.Save


/**
 * @author Master255 on 28.08.2021
 * masters@inbox.ru
 */
interface ViewModel<T : CommunicationModel> : Observe<T>, Save<List<T>> {
}