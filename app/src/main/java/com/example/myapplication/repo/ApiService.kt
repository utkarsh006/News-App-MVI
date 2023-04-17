package com.example.myapplication.repo

import retrofit2.http.GET

interface ApiService {

    @GET("latest_headlines?countries=US&topic=business")
    suspend fun getNews():GetNewsModel
}