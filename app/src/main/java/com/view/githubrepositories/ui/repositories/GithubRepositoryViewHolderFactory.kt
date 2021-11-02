package com.view.githubrepositories.ui.repositories

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

interface GithubRepositoryViewHolderFactory : Abstract.FactoryMapper<Pair<Int,ViewGroup>, GithubRepositoryAdapter.GithubRepositoryViewHolder>{

    class Base(
        private val onItemClickListener: GithubOnItemClickListener<Pair<String,String>>,
        private val collapseOrExpandListener: CollapseOrExpandStateListener<UiGithubRepositoryState>
    ) : GithubRepositoryViewHolderFactory, AbstractViewHolderFactory() {

        override fun map(src: Pair<Int,ViewGroup>): GithubRepositoryAdapter.GithubRepositoryViewHolder = when(src.first) {
            1 -> GithubRepositoryAdapter.GithubRepositoryViewHolder.Progress(
                R.layout.progress.makeView(src.second)
            )
            2 -> GithubRepositoryAdapter.GithubRepositoryViewHolder.Base(
                R.layout.github_repository_layout.makeView(src.second),
                onItemClickListener,
                collapseOrExpandListener
            )
            3 -> GithubRepositoryAdapter.GithubRepositoryViewHolder.Empty(
                R.layout.empty.makeView(src.second)
            )
            else -> GithubRepositoryAdapter.GithubRepositoryViewHolder.Failure(
                R.layout.failure.makeView(src.second)
            )
        }
    }
}


