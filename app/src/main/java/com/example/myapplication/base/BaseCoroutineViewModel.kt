package com.example.myapplication.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.onClosed
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.selects.whileSelect

@FlowPreview
@ExperimentalCoroutinesApi
abstract class BaseCoroutineViewModel<
        S : UiState,
        P : UiState.Partial<S>,
        E : BaseViewEvent,
        I : UserIntent,
        >
protected constructor(
    private val initialState: S,
) : CoroutineViewModel<S, E, I>, ResultWrapper, ViewModel() {

    private val stateRelay: MutableStateFlow<S> = MutableStateFlow(initialState)
    protected val currentState get() = stateRelay.value
    final override val states: StateFlow<S> = stateRelay.asStateFlow()

    private val intentRelay: MutableSharedFlow<I> = MutableSharedFlow()
    final override fun pushIntent(intent: I) = viewModelScope.launch { intentRelay.emit(intent) }
    protected val intents: Flow<I> = intentRelay.asSharedFlow()

    private val viewEventsRelay: Channel<E> = Channel(Channel.BUFFERED)
    protected suspend fun emitViewEvent(event: E) = viewEventsRelay.send(event)
    final override val viewEvents: Flow<E> = viewEventsRelay.receiveAsFlow()

    override val start by lazy { setupState() }

    final override suspend fun attachIntents(intents: Flow<I>) {
        withContext(Dispatchers.IO) { intents.collect { pushIntent(it) } }
    }

    protected abstract fun partialStates(): Flow<P>

    protected abstract fun reduce(currentState: S, partialState: P): S

    protected inline fun <reified UI : I> intent(): Flow<UI> = intents.filterIsInstance()

    // Called once
    private fun setupState() {
        partialStates()
            .scan(initialState, this::reduce)
            .stateThrottle(32)
            .onEach { stateRelay.emit(it) }
            .launchIn(viewModelScope)
    }
}

@Suppress("EXPERIMENTAL_API_USAGE")
fun <X> Flow<X>.stateThrottle(timeMillis: Long) = flow {
    coroutineScope {
        var ticker: Job? = null
        val tickIn = { t: Long -> launch { delay(t) } }

        val upstream = produceIn(this)
        upstream.receiveCatching().also { result ->
            result
                .onSuccess { emit(it) }
                .onFailure { it?.let { throw it } }
                .onClosed { return@coroutineScope }
        }

        var pending: X? = null
        val pushPending: suspend () -> X? = { pending?.also { pending = null; emit(it) } }

        whileSelect {
            ticker?.apply {
                onJoin {
                    pushPending()
                    ticker = null
                    true
                }
            }

            upstream.onReceiveCatching { result ->
                result
                    .onSuccess {
                        pending = it
                        ticker ?: run { ticker = tickIn(timeMillis) }
                    }
                    .onFailure { e ->
                        e?.let { throw e }

                        pushPending()
                        ticker?.cancel()
                    }
                    .isSuccess
            }
        }
    }
}
