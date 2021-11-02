package com.view.githubrepositories.ui.users

import com.view.githubrepositories.core.Abstract


/**
 * @author Master255 on 07.09.2021
 * masters@inbox.ru
 */
interface UserMappersStore {

    fun uiUserMapper() : Abstract.UserMapper<UiGithubUser>

    fun uiUserStateMapper() : Abstract.UserMapper<UiGithubUserState>

    fun uiGithubExceptionMapper() : UiGithubExceptionMapper


    class Base(
        private val uiUserMapper: Abstract.UserMapper<UiGithubUser>,
        private val uiUserStateMapper: Abstract.UserMapper<UiGithubUserState>,
        private val uiDownloadRepositoryMapper: UiGithubExceptionMapper,
    ) : UserMappersStore {

        override fun uiUserMapper(): Abstract.UserMapper<UiGithubUser>
            = uiUserMapper

        override fun uiUserStateMapper(): Abstract.UserMapper<UiGithubUserState>
            = uiUserStateMapper

        override fun uiGithubExceptionMapper(): UiGithubExceptionMapper
            = uiDownloadRepositoryMapper

    }
}