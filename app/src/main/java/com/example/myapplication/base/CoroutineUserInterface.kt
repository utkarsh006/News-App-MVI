package com.example.myapplication.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

interface CoroutineUserInterface<in S : UiState, in E : BaseViewEvent, out I : UserIntent> {

    fun userIntents(): Flow<I> = emptyFlow()

    fun render(state: S) {}

    fun handleViewEvent(event: E) {}
}
