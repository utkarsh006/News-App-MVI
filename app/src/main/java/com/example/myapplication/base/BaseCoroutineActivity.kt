package com.example.myapplication.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
//import dagger.android.AndroidInjection
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@ExperimentalCoroutinesApi
abstract class BaseCoroutineActivity<S : UiState, E : BaseViewEvent, I : UserIntent> :
    AppCompatActivity(),
    CoroutineUserInterface<S, E, I> {

    @Inject
    lateinit var viewModel: CoroutineViewModel<S, E, I>

    protected fun getCurrentState() = viewModel.states.value

    protected fun pushIntent(intent: I) = viewModel.pushIntent(intent)

    private val subscriptions: Job = SupervisorJob()
    private val subscriptionsScope: CoroutineScope = CoroutineScope(subscriptions)

    override fun onCreate(savedInstanceState: Bundle?) {
        //AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        viewModel.viewEvents
            .onEach { handleViewEvent(it) }
            .catch { }
            .launchIn(lifecycleScope)
    }

    override fun onPause() {
        subscriptions.cancelChildren()

        super.onPause()
    }

    override fun onResume() {
        super.onResume()

        subscriptionsScope.launch { renderIncomingStates() }
        subscriptionsScope.launch { viewModel.attachIntents(userIntents()) }
    }

    private suspend fun renderIncomingStates() {
        viewModel.states
            .onEach { withContext(Dispatchers.Main) { render(it) } }
            .catch { }
            .collect()
    }
}
