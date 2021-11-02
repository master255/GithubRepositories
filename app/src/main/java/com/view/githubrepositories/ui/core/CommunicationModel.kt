package com.view.githubrepositories.ui.core


/**
 * @author Master255 on 23.08.2021
 * masters@inbox.ru
 */

interface CommunicationModel {

    interface ItemCommunicationModel : CommunicationModel, Matcher.CommunicationMatcher<ItemCommunicationModel> {
        fun isBase() : Boolean

        fun isCollapsed() : Boolean
    }
}

