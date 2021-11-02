package com.view.githubrepositories.data.repositories.cloud

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @author Master255 on 21.08.2021
 * masters@inbox.ru
 * BASE URL https://api.github.com/
 */
interface GithubRepositoryService {

    @GET("users/{username}/repos")
    fun repositories(@Path("username") owner: String) : Single<List<CloudGithubRepository>>

    @GET("repos/{username}/{repo}")
    fun repository(@Path("username") owner: String, @Path("repo") repo: String) : Single<CloudGithubRepository>
}