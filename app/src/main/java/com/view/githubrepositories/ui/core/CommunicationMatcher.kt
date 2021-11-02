package com.view.githubrepositories.ui.core


/**
 * @author Master255 on 31.08.2021
 * masters@inbox.ru
 */

interface Matcher<T> {

    fun matches(model: T) : Boolean

    interface CommunicationMatcher<T : CommunicationModel.ItemCommunicationModel> : Matcher<T>


    class TestMatcherModel(private val data: String) : Matcher<String> {
        override fun matches(model: String): Boolean
            = data == model
    }
}


