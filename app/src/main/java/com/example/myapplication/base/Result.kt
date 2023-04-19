package com.example.myapplication.base

sealed class Result<T> {
    class Progress<T> : Result<T>()
    data class Success<T>(val value: T) : Result<T>()
    data class Failure<T>(val error: Throwable) : Result<T>()
}
