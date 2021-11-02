package com.view.githubrepositories.data.repositories.download

import com.view.githubrepositories.core.Resource
import com.view.githubrepositories.domain.core.DomainDownloadExceptionMapper
import com.view.githubrepositories.domain.repositories.download.DomainDownloadRepositoryMapper
import com.view.githubrepositories.ui.repositories.download.UiDownloadExceptionMapper
import com.view.githubrepositories.ui.repositories.download.UiDownloadRepositoryMapper
import com.view.githubrepositories.ui.repositories.download.UiGithubDownloadFileState
import org.junit.Assert.*
import org.junit.Test
import java.net.UnknownHostException

/**
 * Test for [TestDataDownloadFile]
 **/

class DataDownloadFileTest {

    @Test
    fun success_map_model() {
        val dataModel = DataDownloadFile.Failure(UnknownHostException("No connection"))
        val domainModel = dataModel.map(
            DomainDownloadRepositoryMapper(
                DomainDownloadExceptionMapper.Base()
            )
        )

        val resourceTest = Resource.Test()
        val uiModel = domainModel.map(UiDownloadRepositoryMapper(
            resourceTest,
            UiDownloadExceptionMapper.Base(resourceTest)
        ))

        val uiStateModel = uiModel.mapTo(Unit)

        val expected = true
        val actual = uiStateModel is UiGithubDownloadFileState.Failure

        assertEquals(expected, actual)

        val expectedErrorMessage = "No connection"
        val actualErrorMessage = uiStateModel.errorMessage()

        assertEquals(expectedErrorMessage,actualErrorMessage)
    }
}