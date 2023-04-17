package com.example.myapplication.repo

import kotlinx.coroutines.flow.Flow


interface NewRepository {
    suspend fun getNews(): Flow<ApiState<GetNewsModel>>
}