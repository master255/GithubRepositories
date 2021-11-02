package com.view.githubrepositories.data.core


import com.view.githubrepositories.data.repositories.cache.RepositoryDao
import com.view.githubrepositories.data.users.cache.UserDao


/**
 * @author Master255 on 23.08.2021
 * masters@inbox.ru
 */

@androidx.room.Dao
interface GithubDao : Dao, UserDao, RepositoryDao