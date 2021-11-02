package com.view.githubrepositories.data.repositories.download.file

import com.view.githubrepositories.R
import com.view.githubrepositories.core.Abstract
import com.view.githubrepositories.core.Resource
import com.view.githubrepositories.data.repositories.download.file.exception.WritingFileException

interface WritingFIleExceptionMapper : Abstract.FactoryMapper<Exception,String> {

    class Base (
        private val resource: Resource
    ) : WritingFIleExceptionMapper {

        override fun map(src: Exception): String
                = when(src) {
            is WritingFileException -> resource.string(R.string.waiting_while_previous_fle_will_be_saved)
            else -> resource.string(R.string.generic_error)
        }
    }
}
