package com.view.githubrepositories.data.repositories.download.file


/**
 * @author Master255 on 07.09.2021
 * masters@inbox.ru
 */


interface SizeFile {

    fun isBigSizeFile(size: Int) : Boolean

    class Base : SizeFile {

        override fun isBigSizeFile(size: Int): Boolean {
            return size > BIG_FILE_SIZE
        }

        /**
         * BIG_SIZE_FILE > 80 KBs
         */
        private companion object {
            const val BIG_FILE_SIZE = 80
        }
    }

    class Test : SizeFile {

        override fun isBigSizeFile(size: Int): Boolean {
            return size > BIG_FILE_SIZE
        }

        private companion object {
            const val BIG_FILE_SIZE = 80
        }
    }

}