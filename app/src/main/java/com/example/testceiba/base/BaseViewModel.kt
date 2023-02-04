package com.example.testceiba.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class BaseViewModel<State, Event>(
    private val viewState: State
) : ViewModel() {

    private val mutableState =
        MutableSharedFlow<State>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    val state: Flow<State> get() = mutableState

    protected val mutableEvent = MutableSharedFlow<Event>(extraBufferCapacity = 1)

    val events: Flow<Event> get() = mutableEvent

    private val mutex: Mutex = Mutex()

    protected suspend fun updateState(reducer: (State) -> State) {
        mutex.withLock {
            mutableState.emit(reducer(currentState()))
        }
    }

    protected fun currentState() = mutableState.replayCache.firstOrNull() ?: viewState
}