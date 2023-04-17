package com.example.myapplication.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class RepoImpl(val apiService: ApiService) : NewRepository {
    override suspend fun getNews(): Flow<ApiState<GetNewsModel>> {
        return flow {
            emit(ApiState.Loading)
            val data = apiService.getNews()
            emit(ApiState.Success(data))
        }.catch { ex ->
            emit(ApiState.Error(ex))
        }
    }
}