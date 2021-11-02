package com.view.githubrepositories.data.users.cloud

import com.google.gson.annotations.SerializedName
import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.data.users.DataGithubUser
import com.view.githubrepositories.data.users.cache.CacheGithubUser


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */
data class CloudGithubUser(
    @SerializedName("login")
    private val name: String,
    @SerializedName("bio")
    private val bio: String?,
    @SerializedName("avatar_url")
    private val profileImageUrl: String
) : Abstract.Object.Cache.GithubUser<CacheGithubUser> {

    override fun map(mapper: Abstract.UserMapper<DataGithubUser>): DataGithubUser =
        conditionMap(mapper)

    override fun map(mapper: Abstract.UserMapper<CacheGithubUser>): CacheGithubUser =
        conditionMap(mapper)

    private fun <T> conditionMap(mapper: Abstract.UserMapper<T>): T = if (bio == null) {
        mapper.map(name, "Empty bio", profileImageUrl,true)
    } else {
        mapper.map(name, bio, profileImageUrl,true)
    }

}
