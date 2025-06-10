package com.ebc.randomsouthpark.controller


import com.ebc.randomsouthpark.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApiService {
    @GET("episodes/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): Response<ApiResponse>
}