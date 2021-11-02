package com.view.githubrepositories.ui.core.adapter

import com.view.githubrepositories.ui.core.CommunicationModel


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
interface GithubAdapter<T : CommunicationModel.ItemCommunicationModel> {

    fun update(list: List<T>)

    fun update(item: T,position: Int)
}



interface GithubOnItemClickListener<T> {

    fun onItemClick(data: T)
}