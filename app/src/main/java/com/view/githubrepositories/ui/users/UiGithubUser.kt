package com.view.githubrepositories.ui.users

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.core.Same
import com.view.githubrepositories.data.users.cache.CacheGithubUser
import com.view.githubrepositories.ui.core.view.AbstractView
import com.view.githubrepositories.ui.core.adapter.AbstractListener
import com.view.githubrepositories.ui.core.adapter.GithubOnItemClickListener


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */

open class UiGithubUser(
    private val name: String,
    private val bio: String,
    private val profileImageUrl: String,
    private val isCollapsed: Boolean
) : Abstract.Object.Ui.GithubUser<UiGithubUserState>,
    Abstract.FactoryMapper<List<AbstractView>,Unit>,
    AbstractListener<GithubOnItemClickListener<String>>,
    Abstract.UniqueMapper<CacheGithubUser,Boolean>,
    Same<UiGithubUser> {

    class Mock : UiGithubUser(
        "",
        "",
        "",
        true
    )

    override fun map(mapper: Abstract.UserMapper<UiGithubUserState>): UiGithubUserState
        = mapper.map(name,bio,profileImageUrl,isCollapsed)

    override fun map(src: List<AbstractView>)
        = src.forEach { view -> view.map(name,bio,profileImageUrl,isCollapsed) }

    override fun mapTo(isCollapsed: Boolean) : CacheGithubUser
        = CacheGithubUser(name, bio, profileImageUrl, isCollapsed)

    override fun notifyAboutItemClick(listener: GithubOnItemClickListener<String>)
        = listener.onItemClick(name)

    override fun same(element: UiGithubUser) : Boolean
        = element.same(name, bio)

    private fun same(name: String,bio: String)
        = this.name == name && this.bio == bio

}