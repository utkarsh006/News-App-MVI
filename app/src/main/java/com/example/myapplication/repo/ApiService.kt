package com.example.myapplication.repo

import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("x-api-key: hC8-89M2-kMdLD1JT5LE8lxUltj4_XAkV_KQNyQTa88")
    @GET("latest_headlines?countries=US&topic=business")
    suspend fun getNews():GetNewsModel
}