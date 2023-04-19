package com.example.myapplication.ui

import com.example.myapplication.base.BaseViewEvent
import com.example.myapplication.base.UiState
import com.example.myapplication.base.UserIntent


interface HomeContract {

    data class State(
        val showProgressBar: Boolean = false,
    ) : UiState

    sealed class PartialState : UiState.Partial<State> {
        data class SetProgressBarVisibility(val isVisible: Boolean) : PartialState()
    }

    sealed class Intent : UserIntent {
        data class EnterTodo(val value: String) : Intent()

        object AddTodoClicked : Intent()

        object PressBackOnTodoFocus : Intent()

        object PressComposeView : Intent()

        data class SaveTodo(val title: String) : Intent()

        data class UpdateTodoStatus(val id: Int, val isComplete: Boolean) : Intent()
    }

    sealed class ViewEvent : BaseViewEvent {
        data class ShowToast(val text: String) : ViewEvent()

        object ShowAddTodoDialog : ViewEvent()

        object GoToComposeView : ViewEvent()
    }
}