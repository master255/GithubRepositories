package com.view.githubrepositories.ui.repositories.download

import com.view.githubrepositories.domain.core.DomainDownloadExceptionMapper


/**
 * @author Master255 on 07.09.2021
 * masters@inbox.ru
 */
interface DownloadRepositoryExceptionMappersStore {

    fun domainDownloadExceptionMapper() : DomainDownloadExceptionMapper

    fun uiDownloadExceptionMapper() : UiDownloadExceptionMapper

    class Base(
        private val domainDownloadExceptionMapper: DomainDownloadExceptionMapper,
        private val uiDownloadExceptionMapper: UiDownloadExceptionMapper
    ) : DownloadRepositoryExceptionMappersStore {

        override fun domainDownloadExceptionMapper(): DomainDownloadExceptionMapper
            = domainDownloadExceptionMapper

        override fun uiDownloadExceptionMapper(): UiDownloadExceptionMapper
            = uiDownloadExceptionMapper
    }
}