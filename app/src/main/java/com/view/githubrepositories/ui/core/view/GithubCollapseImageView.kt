package com.view.githubrepositories.ui.core.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import com.view.githubrepositories.R


/**
 * @author Master255 on 25.08.2021
 * masters@inbox.ru
 */
class GithubCollapseImageView : androidx.appcompat.widget.AppCompatImageView, CollapseView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    //endregion

    override fun map(isCollapsed: Boolean) {
        if (isCollapsed) {
            image(R.drawable.ic_collapse)
        } else {
            image(R.drawable.ic_expand)
        }
    }

    private fun image(@DrawableRes drawableRes: Int)
        = this.setImageDrawable(resources.getDrawable(drawableRes))
}