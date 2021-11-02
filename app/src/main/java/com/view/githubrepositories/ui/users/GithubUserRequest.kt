package com.view.githubrepositories.ui.users



/**
 * @author Master255 on 20.08.2021
 * masters@inbox.ru
 */
interface GithubUserRequest<T> {

    fun data(param: T)
}