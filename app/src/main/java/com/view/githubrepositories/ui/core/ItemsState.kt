package com.view.githubrepositories.ui.core

import com.view.githubrepositories.data.core.prefs.CachedState
import com.view.githubrepositories.ui.users.CollapseOrExpandState


/**
 * @author Master255 on 30.08.2021
 * masters@inbox.ru
 */

interface ItemsState<T> {

    fun updateState(state: T)

    fun currentState() : T

    class Base(
        cachedState: CachedState,
        private var currentItemsState: CollapseOrExpandState = CollapseOrExpandState.Empty
    ) : ItemsState<CollapseOrExpandState> {

        init {
            currentItemsState = cachedState.currentState()
        }

        override fun updateState(state: CollapseOrExpandState) {
            currentItemsState = state
        }

        override fun currentState() = currentItemsState
    }

    class Test : ItemsState<Test.TestItemsState> {

        private var currentState: TestItemsState = TestItemsState.UNKNOWN


        override fun updateState(state: TestItemsState) {
            currentState = state
        }

        override fun currentState(): TestItemsState = currentState

        enum class TestItemsState {
            GOOD,BAD,UNKNOWN
        }
    }
}