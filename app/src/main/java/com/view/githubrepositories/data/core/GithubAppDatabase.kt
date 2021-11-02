package com.view.githubrepositories.data.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.view.githubrepositories.data.repositories.cache.CacheGithubRepository
import com.view.githubrepositories.data.users.cache.CacheGithubUser


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */

@Database(entities = [CacheGithubRepository::class,CacheGithubUser::class], version = 1, exportSchema = false)
abstract class GithubAppDatabase :  RoomDatabase() {

    abstract fun dao() : GithubDao

    companion object {
        private var instance: GithubAppDatabase? = null

        @Synchronized
        fun database(context: Context) : GithubAppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    GithubAppDatabase::class.java,
                    NAME_DB,
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }

        private const val NAME_DB = "github_app_db"
    }


}