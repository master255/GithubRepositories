package com.view.githubrepositories.ui.repositories.download

import com.view.githubrepositories.R
import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.core.Resource
import com.view.githubrepositories.domain.repositories.download.exceptions.NoConnectionException
import com.view.githubrepositories.domain.repositories.download.exceptions.WorkServerException
import com.view.githubrepositories.domain.repositories.download.exceptions.ZipFileNotFound
import java.lang.Exception


/**
 * @author Master255 on 05.09.2021
 * masters@inbox.ru
 */

interface UiDownloadExceptionMapper : Abstract.FactoryMapper<Exception, String> {

    class Base(
        private val resource: Resource
    ) : UiDownloadExceptionMapper{

        override fun map(src: Exception): String = when(src) {
            is NoConnectionException -> resource.string(R.string.no_connection_error)
            is WorkServerException -> resource.string(R.string.server_error)
            is ZipFileNotFound -> resource.string(R.string.file_not_found_error)
            else -> resource.string(R.string.generic_error)
        }
    }
}

