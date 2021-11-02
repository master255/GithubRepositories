package com.view.githubrepositories.data.repositories.download.cloud

import io.reactivex.Single
import okhttp3.ResponseBody


/**
 * @author Master255 on 21.08.2021
 * masters@inbox.ru
 */
interface GithubDownloadRepoCloudDataSource {

    fun download(owner: String, repo: String) : Single<ResponseBody>

    class Base(
        private val githubRepositoryService: GithubDownloadRepoService
    ) : GithubDownloadRepoCloudDataSource {

        override fun download(owner: String, repo: String): Single<ResponseBody>
            = githubRepositoryService.downloadRepository(owner,repo)
    }
}