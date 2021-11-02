package com.view.githubrepositories.domain.repositories.download

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.domain.core.DomainDownloadExceptionMapper
import okhttp3.ResponseBody


/**
 * @author Master255 on 05.09.2021
 * masters@inbox.ru
 */
class DomainDownloadRepositoryMapper(
    private val exceptionMapper: DomainDownloadExceptionMapper,
) : Abstract.DownloadFileMapper<DomainDownloadFile> {

    override fun map(): DomainDownloadFile
        = DomainDownloadFile.Exist

    override fun map(size: Int,data: ResponseBody): DomainDownloadFile
        = DomainDownloadFile.Big(size,data)

    override fun map(data: ResponseBody): DomainDownloadFile
        = DomainDownloadFile.WaitingToDownload(data)

    override fun map(e: Exception): DomainDownloadFile {
        val ioException = exceptionMapper.map(e)
        return DomainDownloadFile.Failure(ioException)
    }
}