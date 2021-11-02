package com.view.githubrepositories.ui.users

import com.view.githubrepositories.R
import com.view.githubrepositories.core.Resource


sealed class CollapseOrExpandState {

    open fun asString() = ""

    object Empty : CollapseOrExpandState()

    class Collapsed(private val resource: Resource) : CollapseOrExpandState() {

        override fun asString(): String
            = resource.string(R.string.collapse_text)
    }
    class Expanded(private val resource: Resource) : CollapseOrExpandState() {

        override fun asString(): String
            = resource.string(R.string.expand_text)
    }
    class Any(private val resource: Resource) : CollapseOrExpandState() {

        override fun asString(): String
            = resource.string(R.string.any_text)
    }

}
