package com.example.myapplication.usecase

import com.example.myapplication.repo.ApiState
import com.example.myapplication.repo.GetNewsModel
import com.example.myapplication.repo.NewRepository
import com.example.myapplication.repo.RepoImpl
import kotlinx.coroutines.flow.Flow

class GetNewUseCaseImpl(private val repository: NewRepository) :GetNewUseCase {
    override suspend fun invoke(): Flow<ApiState<GetNewsModel>> {
        return repository.getNews()
    }

}