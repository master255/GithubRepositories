package com.view.githubrepositories.ui.users

import com.view.githubrepositories.core.DisposableStore
import com.view.githubrepositories.domain.users.GithubUserInteractor
import com.view.githubrepositories.ui.core.BaseGithubUserRequest


/**
 * @author Master255 on 24.08.2021
 * masters@inbox.ru
 */

class Remote(
    githubUserInteractor: GithubUserInteractor,
    communication: GithubUserCommunication,
    githubUserDisposableStore: DisposableStore,
    uiMappersStore: UserMappersStore
) : BaseGithubUserRequest(
    githubUserInteractor,
    communication,
    githubUserDisposableStore,
    uiMappersStore
)
