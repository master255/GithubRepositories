package com.view.githubrepositories.ui.users.cache

import com.view.githubrepositories.core.DisposableStore
import com.view.githubrepositories.core.Resource
import com.view.githubrepositories.domain.users.GithubUserInteractor
import com.view.githubrepositories.ui.users.*



/**
 * @author Master255 on 20.08.2021
 * masters@inbox.ru
 */

class Local(
    githubUserInteractor: GithubUserInteractor,
    communication: GithubUserCommunication,
    githubUserDisposableStore: DisposableStore,
    resource: Resource,
    userMappersStore: UserMappersStore
) : LocalGithubUserRequest(
    githubUserInteractor,
    communication,
    githubUserDisposableStore,
    resource,
    userMappersStore
)