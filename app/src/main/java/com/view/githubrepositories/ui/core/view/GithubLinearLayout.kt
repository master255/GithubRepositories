package com.view.githubrepositories.ui.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout


/**
 * @author Master255 on 25.08.2021
 * masters@inbox.ru
 */
class GithubLinearLayout : LinearLayout, CollapseView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    //endregion


    override fun map(isCollapsed: Boolean) = if(isCollapsed)
            visibility(View.GONE)
        else
            visibility(View.VISIBLE)


    private fun visibility(visibility: Int) {
        this.visibility = visibility
    }
}