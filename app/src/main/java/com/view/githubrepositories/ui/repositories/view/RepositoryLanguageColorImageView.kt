package com.view.githubrepositories.ui.repositories.view

import android.content.Context
import android.util.AttributeSet
import com.view.githubrepositories.ui.core.view.GithubImageView
import com.view.githubrepositories.ui.core.view.GithubViewType


/**
 * @author Master255 on 20.08.2021
 * masters@inbox.ru
 */
class RepositoryLanguageColorImageView : GithubImageView {

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    //endregion

    override fun githubViewType(): GithubViewType
        = GithubViewType.RepositoryLanguageColorImage
}