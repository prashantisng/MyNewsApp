package com.outthinking.newsmvvmex.api

import com.outthinking.newsmvvmex.models.NewsResponse
import com.outthinking.newsmvvmex.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey:String= Constants.API_KEY
    ): Response<NewsResponse>



    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey:String= Constants.API_KEY
    ): Response<NewsResponse>

}