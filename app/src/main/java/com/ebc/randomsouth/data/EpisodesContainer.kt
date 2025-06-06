package com.ebc.randomsouth.data

import com.ebc.randomsouth.controller.EpisodesAPIService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface EpisodesContainer {
    //puedo tener varias url, varias definiciones del contenedor
    val episodesRepository: EpisodesRepository
}

class DefaultEpisodesContainer: EpisodesContainer {
    private val BASE_URL = "https://spapi.dev/api/episodes/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: EpisodesAPIService by lazy {
        retrofit.create(EpisodesAPIService::class.java)
    }

    override val episodesRepository: EpisodesRepository by lazy {
        DefaultEpisodeRepository(retrofitService)
    }

}