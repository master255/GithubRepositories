package com.view.githubrepositories.ui.repositories.download

import com.view.githubrepositories.R
import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.core.Resource
import okhttp3.ResponseBody


/**
 * @author Master255 on 05.09.2021
 * masters@inbox.ru
 */
sealed class UiDownloadFile :
    Abstract.UniqueMapper<UiGithubDownloadFileState,Unit>
{


    object Exist : UiDownloadFile() {

        override fun mapTo(param: Unit): UiGithubDownloadFileState
            = UiGithubDownloadFileState.Exist
    }

    class Big(
        private val resource: Resource,
        private val size: Int,
        private val data: ResponseBody
    ) : UiDownloadFile() {

        override fun mapTo(param: Unit): UiGithubDownloadFileState
            = UiGithubDownloadFileState.Big(
                size,
                resource.string(R.string.file_is_big),
                data
            )
    }

    class WaitingToDownload(
        private val data: ResponseBody
    ) : UiDownloadFile() {

        override fun mapTo(param: Unit): UiGithubDownloadFileState
            = UiGithubDownloadFileState.WaitingToDownload(data)
    }

    class Failure(
            private val e: Exception,
            private val exceptionMapper: UiDownloadExceptionMapper
        ) : UiDownloadFile() {

        override fun mapTo(param: Unit): UiGithubDownloadFileState
            = UiGithubDownloadFileState.Failure(
                exceptionMapper.map(e)
            )
    }
}