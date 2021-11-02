package com.view.githubrepositories.core

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * @author Master255 on 19.08.2021
 * masters@inbox.ru
 */
interface DisposableStore {

    fun add(disposable: Disposable)
    fun dispose()

    class Base(
        private val compositeDisposable: CompositeDisposable
    ): DisposableStore {

        override fun add(disposable: Disposable) {
            compositeDisposable.add(disposable)
        }

        override fun dispose() = compositeDisposable.dispose()
    }
}