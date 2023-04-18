package com.example.myapplication.repo

import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers("x-api-key: GeAIx0i_a2AJXk6orSNWP9cicDM1XivuIuRBuAP3vzQ")
    @GET("latest_headlines?countries=US&topic=business")
    suspend fun getNews():GetNewsModel
}