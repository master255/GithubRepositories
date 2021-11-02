package com.view.githubrepositories.ui.core

import com.view.githubrepositories.core.DisposableStore
import io.reactivex.disposables.Disposable


/**
 * @author Master255 on 21.08.2021
 * masters@inbox.ru
 */

interface CleanDisposable {

    fun Disposable.addToDisposableStore(store: DisposableStore)
}