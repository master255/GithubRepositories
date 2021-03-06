package com.view.githubrepositories.data.repositories.download.file

import java.io.File

interface CheckFileByExist {

    fun isNotExist(path: String) : Boolean

    class Base : CheckFileByExist {

        override fun isNotExist(path: String) : Boolean {
            val file = File(path)
            return if (file.exists()) {
                checkSizeFile(file)
            } else {
                true
            }
        }

        private fun checkSizeFile(file: File) : Boolean
            = file.length().toInt() <= SIZE_NOT_EXIST_FILE

        private companion object {
            const val SIZE_NOT_EXIST_FILE = 0
        }
    }
}