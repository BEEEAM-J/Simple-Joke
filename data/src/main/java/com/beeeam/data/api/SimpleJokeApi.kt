package com.beeeam.data.api

import com.beeeam.data.response.CategoryResponse
import com.beeeam.data.response.JokeResponse
import com.beeeam.data.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleJokeApi {
    @GET("jokes/categories")
    suspend fun getCategoryList(): Response<CategoryResponse>

    @GET("jokes/random")
    suspend fun getJoke(
        @Query("category") category: String?,
    ): Response<JokeResponse>

    @GET("jokes/search")
    suspend fun searchJoke(
        @Query("query") query: String?,
    ): Response<SearchResponse>
}