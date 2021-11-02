package com.view.githubrepositories.ui.core.adapter



/**
 * @author Master255 on 25.08.2021
 * masters@inbox.ru
 */

interface AbstractListener<T> {

    fun notifyAboutItemClick(listener: T)

    interface CollapseOrExpandListener<T,C> : AbstractListener<T> {
       fun notifyAboutCollapseOrExpand(listener: C, position: Int)
    }
}