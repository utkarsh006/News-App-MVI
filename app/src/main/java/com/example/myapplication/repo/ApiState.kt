package com.example.myapplication.repo

sealed class ApiState<out T>{
    object Loading:ApiState<Nothing>()
    data class Success<T>(val data:T):ApiState<T>()
    data class Error(val e:Throwable):ApiState<Nothing>()
}
