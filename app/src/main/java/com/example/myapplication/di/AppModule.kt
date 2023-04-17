package com.example.myapplication.di

import com.example.myapplication.repo.ApiService
import com.example.myapplication.repo.NewRepository
import com.example.myapplication.repo.RepoImpl
import com.example.myapplication.usecase.GetNewUseCase
import com.example.myapplication.usecase.GetNewUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideApiRepo(apiService: ApiService): NewRepository {
        return RepoImpl(apiService)
    }

    @Provides
    fun provideUseCase(repository: NewRepository): GetNewUseCase {
        return GetNewUseCaseImpl(repository)
    }

}

const val BASE_URL = "https://api.newscatcherapi.com/v2/"