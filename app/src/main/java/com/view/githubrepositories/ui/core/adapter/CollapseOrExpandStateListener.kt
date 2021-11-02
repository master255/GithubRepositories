package com.view.githubrepositories.ui.core.adapter

import com.view.githubrepositories.ui.core.CommunicationModel


/**
 * @author Master255 on 31.08.2021
 * masters@inbox.ru
 */
interface CollapseOrExpandStateListener<T : CommunicationModel.ItemCommunicationModel> {

    fun onChangeCollapseState(item: T,position: Int)
}