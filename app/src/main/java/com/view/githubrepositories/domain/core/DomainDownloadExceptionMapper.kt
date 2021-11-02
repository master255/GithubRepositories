package com.view.githubrepositories.domain.core

import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.domain.repositories.download.exceptions.NoConnectionException
import com.view.githubrepositories.domain.repositories.download.exceptions.SomeWentWrongException
import com.view.githubrepositories.domain.repositories.download.exceptions.WorkServerException
import com.view.githubrepositories.domain.repositories.download.exceptions.ZipFileNotFound
import retrofit2.HttpException
import java.io.FileNotFoundException
import java.io.IOException
import java.net.UnknownHostException


/**
 * @author Master255 on 05.09.2021
 * masters@inbox.ru
 */

interface DomainDownloadExceptionMapper : Abstract.FactoryMapper<Throwable, IOException> {

    class Base : DomainDownloadExceptionMapper {
        override fun map(src: Throwable): IOException = when(src) {
            is UnknownHostException -> NoConnectionException()
            is retrofit2.adapter.rxjava2.HttpException, is HttpException -> WorkServerException()
            is FileNotFoundException -> ZipFileNotFound()
            else -> SomeWentWrongException()
        }
    }
}
