package com.view.githubrepositories.data.users.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.data.users.DataGithubUser


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */

@Entity(tableName = "github_users_table")
data class CacheGithubUser(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "bio")
    val bio: String,
    @ColumnInfo(name = "profileImageUrl")
    val profileImageUrl: String,
    @ColumnInfo(name = "collapse")
    val isCollapsed: Boolean
) : Abstract.Object.Data.GithubUser {

    override fun map(mapper: Abstract.UserMapper<DataGithubUser>): DataGithubUser
        = mapper.map(name,bio,profileImageUrl,isCollapsed)
}