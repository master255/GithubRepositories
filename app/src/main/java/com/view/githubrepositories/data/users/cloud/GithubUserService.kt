package com.view.githubrepositories.data.users.cloud

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @author Master255 on 19.08.2021
 * masters@inbox.ru
 * BASE URL https://api.github.com/
 */
interface GithubUserService {

    @GET("users/{username}")
    fun user(@Path("username") query: String) : Single<CloudGithubUser>
}