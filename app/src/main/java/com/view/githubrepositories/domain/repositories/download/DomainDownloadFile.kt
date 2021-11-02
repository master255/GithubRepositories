package com.view.githubrepositories.domain.repositories.download

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.ui.repositories.download.UiDownloadFile
import okhttp3.ResponseBody


/**
 * @author Master255 on 05.09.2021
 * masters@inbox.ru
 */
sealed class DomainDownloadFile : Abstract.Object.Ui.GithubDownloadRepository<UiDownloadFile> {

    object Exist : DomainDownloadFile() {

        override fun map(mapper: Abstract.DownloadFileMapper<UiDownloadFile>): UiDownloadFile
            = mapper.map()
    }

    class Big(
        private val size: Int,
        private val data: ResponseBody
    ) : DomainDownloadFile() {

        override fun map(mapper: Abstract.DownloadFileMapper<UiDownloadFile>): UiDownloadFile
            = mapper.map(size,data)
    }

    class WaitingToDownload(
        private val data: ResponseBody
    ) : DomainDownloadFile() {

        override fun map(mapper: Abstract.DownloadFileMapper<UiDownloadFile>): UiDownloadFile
            = mapper.map(data)
    }

    class Failure(private val e: Exception) : DomainDownloadFile() {
        override fun map(mapper: Abstract.DownloadFileMapper<UiDownloadFile>): UiDownloadFile
            = mapper.map(e)
    }
}