package com.example.myapplication.ui

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.BaseCoroutineViewModel
import com.example.myapplication.base.CoroutineViewModel
import com.example.myapplication.intent.MainIntent
import com.example.myapplication.repo.ApiState
import com.example.myapplication.repo.GetNewsModel
import com.example.myapplication.usecase.GetNewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: GetNewUseCase)
    : BaseCoroutineViewModel<HomeContract.State,
        HomeContract.PartialState, HomeContract.ViewEvent, HomeContract.Intent>(HomeContract.State()) {
    override fun partialStates(): Flow<HomeContract.PartialState> {
        TODO("Not yet implemented")
    }

    override fun reduce(
        currentState: HomeContract.State,
        partialState: HomeContract.PartialState
    ): HomeContract.State {
        TODO("Not yet implemented")
    }
}

//    val mainIntent: Channel<MainIntent> = Channel(Channel.UNLIMITED)
//
//    private val mutableState = MutableStateFlow<ApiState<GetNewsModel>>(ApiState.Loading)
//    val state: StateFlow<ApiState<GetNewsModel>>
//        get() = mutableState.asStateFlow()
//
//    init {
//        handleIntent()
//    }
//
//    private fun handleIntent() {
//        viewModelScope.launch {
//            mainIntent.consumeAsFlow().collect {
//                when (it) {
//                    is MainIntent.GetNews -> {
//                        getNews()
//                    }
//                    is MainIntent.Refresh -> {
//                    }
//                }
//            }
//        }
//    }
//
//    private fun getNews() {
//        viewModelScope.launch(Dispatchers.IO) {
//            useCase.invoke().collect {
//                mutableState.value = it
//            }
//        }
//    }

//}