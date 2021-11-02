package com.view.githubrepositories.data.core


/**
 * @author Master255 on 23.08.2021
 * masters@inbox.ru
 */
interface Text {

    fun subText(text: String) : String

    class GithubName : Text {
        override fun subText(text: String) = if (text.length > 15) {
            "${text.substring(0,15)}..."
        } else {
            text
        }
    }

    class Test : Text {
        override fun subText(text: String) = if (text.length > 15) {
            "${text.substring(0,15)}..."
        } else {
            text
        }
    }
}