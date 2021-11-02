package com.view.githubrepositories.ui.users

import android.view.ViewGroup
import com.view.githubrepositories.R
import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.ui.core.AbstractViewHolderFactory
import com.view.githubrepositories.ui.core.adapter.CollapseOrExpandStateListener
import com.view.githubrepositories.ui.core.adapter.GithubOnItemClickListener


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */

interface GithubUserViewHolderFactory : Abstract.FactoryMapper<Pair<Int,ViewGroup>, GithubUserAdapter.GithubUserViewHolder> {

    class Base (
        private val listener: GithubOnItemClickListener<String>,
        private val collapseOrExpandListener: CollapseOrExpandStateListener<UiGithubUserState>
    ) : GithubUserViewHolderFactory, AbstractViewHolderFactory() {

        override fun map(src: Pair<Int,ViewGroup>): GithubUserAdapter.GithubUserViewHolder = when(src.first) {
            1 -> GithubUserAdapter.GithubUserViewHolder.Progress(
                R.layout.progress.makeView(src.second)
            )
            2 -> GithubUserAdapter.GithubUserViewHolder.Base(
                R.layout.github_user_layout.makeView(src.second),
                listener,
                collapseOrExpandListener
            )
            3 -> GithubUserAdapter.GithubUserViewHolder.Empty(
                R.layout.empty.makeView(src.second)
            )
            else -> GithubUserAdapter.GithubUserViewHolder.Failure(
                R.layout.failure.makeView(src.second)
            )
        }
    }
}


