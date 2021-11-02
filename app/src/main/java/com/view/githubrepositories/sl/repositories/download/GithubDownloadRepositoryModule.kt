package com.view.githubrepositories.sl.repositories.download

import com.view.githubrepositories.sl.core.BaseModule
import com.view.githubrepositories.sl.core.CoreModule
import com.view.githubrepositories.core.DisposableStore
import com.view.githubrepositories.ui.repositories.download.*
import io.reactivex.disposables.CompositeDisposable


/**
 * @author Master255 on 25.08.2021
 * masters@inbox.ru
 */
class GithubDownloadRepositoryModule(
    private val coreModule: CoreModule
) : BaseModule<WriteFileViewModel.Base> {

    override fun viewModel(): WriteFileViewModel.Base {
        return WriteFileViewModel.Base(
            WriteFileCommunication(),
            coreModule.cachedFile,
            DisposableStore.Base(CompositeDisposable())
        )
    }
}