package com.view.githubrepositories.data.repositories.download.cloud

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Streaming


/**
 * @author Master255 on 05.09.2021
 * masters@inbox.ru
 */
interface GithubDownloadRepoService {

    @Streaming
    @Headers("Accept: application/vnd.github.v3.raw")
    @GET("repos/{owner}/{repo}/zipball")
    fun downloadRepository(@Path("owner") owner: String, @Path("repo") repo: String) : Single<ResponseBody>

}