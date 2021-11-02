package com.view.githubrepositories.data.repositories.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.data.repositories.DataGithubRepository


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */

@Entity(tableName = "github_repo_table",primaryKeys = ["repo","owner"])
data class CacheGithubRepository(
    @ColumnInfo(name = "repo")
    val repo: String,
    @ColumnInfo(name = "lock")
    val lock: Boolean,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "owner")
    val owner: String,
    @ColumnInfo(name = "urlRepository")
    val urlRepository: String,
    @ColumnInfo(name = "defaultBranch")
    val defaultBranch: String,
    @ColumnInfo(name = "collapse")
    val isCollapsed: Boolean
) : Abstract.Object.Data.GithubRepository {

    override fun map(mapper: Abstract.RepositoryMapper<DataGithubRepository>): DataGithubRepository
        = mapper.map(repo,lock,language,owner,urlRepository,defaultBranch, isCollapsed)
}