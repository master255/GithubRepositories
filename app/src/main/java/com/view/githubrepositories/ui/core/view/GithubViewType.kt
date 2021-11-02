package com.view.githubrepositories.ui.core.view


/**
 * @author Master255 on 31.08.2021
 * masters@inbox.ru
 */
sealed class GithubViewType {

    object RepositoryName : GithubViewType()
    object RepositoryLanguage : GithubViewType()
    object RepositoryDefaultBranch : GithubViewType()

    object RepositoryPrivateImage : GithubViewType()
    object RepositoryLanguageColorImage : GithubViewType()


    object UserName : GithubViewType()
    object UserBio : GithubViewType()

    object UserProfileImage : GithubViewType()

    object Error : GithubViewType()
}