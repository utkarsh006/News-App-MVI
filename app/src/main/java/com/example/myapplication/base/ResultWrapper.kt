package com.example.myapplication.base

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
interface ResultWrapper {

    fun <R> drip(s: suspend () -> R): Flow<R> = s.asFlow()

    fun <R> Flow<R>.dropAll(): Flow<Nothing> = transform { }

    fun <R> wrap(s: suspend () -> R): Flow<Result<R>> = wrap(s.asFlow())

    fun <R> wrap(f: Flow<R>) = flow<Result<R>> {
        emit(Result.Progress())
        emitAll(
            f.map { Result.Success(it) }
                .catch { emit(Result.Failure(it)) }
        )
    }
}
