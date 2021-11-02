package com.view.githubrepositories.ui.repositories.view

import androidx.annotation.DrawableRes
import com.view.githubrepositories.R
import com.view.githubrepositories.core.Abstract


/**
 * @author Master255 on 23.08.2021
 * masters@inbox.ru
 */
class DrawableImageFactory : Abstract.FactoryMapper<String,@DrawableRes Int> {

    @DrawableRes
    override fun map(src: String): Int = when(src) {
        "Java" -> R.drawable.ic_circle_orange
        "Python" -> R.drawable.ic_circle_blue_dark
        "Kotlin" -> R.drawable.ic_circle_purple
        "C++","C#","C" -> R.drawable.ic_circle_green
        "Ruby" -> R.drawable.ic_circle_red
        "Go" -> R.drawable.ic_circle_laguna
        "JavaScript" -> R.drawable.ic_circle_yellow
        "PHP","Perl" -> R.drawable.ic_circle_blue_light
        else -> R.drawable.ic_circle_grey
    }

}