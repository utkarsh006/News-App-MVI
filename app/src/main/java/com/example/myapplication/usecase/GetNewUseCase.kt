package com.example.myapplication.usecase

import com.example.myapplication.repo.ApiState
import com.example.myapplication.repo.GetNewsModel
import kotlinx.coroutines.flow.Flow

interface GetNewUseCase {
    suspend fun invoke(): Flow<ApiState<GetNewsModel>>
}