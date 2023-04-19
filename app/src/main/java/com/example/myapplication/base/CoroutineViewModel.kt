package com.example.myapplication.base

//import tech.okcredit.base.delegates.singleViewModelProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.myapplication.ui.MainViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import tech.okcredit.base.delegates.singleViewModelProvider
import javax.inject.Provider

interface CoroutineViewModel<S : UiState, E : BaseViewEvent, I : UserIntent> : LazilyStarted {

    suspend fun attachIntents(intents: Flow<I>)

    fun pushIntent(intent: I): Job

    val states: StateFlow<S>

    val viewEvents: Flow<E>
}

/**
 * This factory is only meant to be used with [CoroutineViewModel]
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T, S, E, I> ViewModelStoreOwner.getCoroutineViewModel(
    provider: Provider<T>,
): T where T : ViewModel, T : CoroutineViewModel<S, E, I> {
    return singleViewModelProvider { provider.get().also { it.start } }.get()

}
