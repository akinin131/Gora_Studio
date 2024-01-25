package com.example.gora_studio.data.network

import com.example.gora_studio.data.network.model.NewsResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("/v2/everything")
    fun getNewsByQueryAndDate(
        @Query("q") query: String,
        @Query("from") fromDate: String,
        @Query("to") toDate: String,
        @Query("sortBy") sortBy: String,
        @Query("pageSize") pageSize: Int = 20
    ): Call<NewsResponseModel>

}
