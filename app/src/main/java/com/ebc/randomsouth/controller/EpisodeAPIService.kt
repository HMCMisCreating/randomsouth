package com.ebc.randomsouth.controller


import com.ebc.randomsouth.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApiService {
    @GET("episodes/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): Response<ApiResponse>
}