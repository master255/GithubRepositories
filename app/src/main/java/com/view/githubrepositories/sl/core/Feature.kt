package com.view.githubrepositories.sl.core


/**
 * @author Master255 on 25.08.2021
 * masters@inbox.ru
 */
sealed class Feature {

    object Repository : Feature()

    object WriteFile : Feature()

    object User : Feature()
}
