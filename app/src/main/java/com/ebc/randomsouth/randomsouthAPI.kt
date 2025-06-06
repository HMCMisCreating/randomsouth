package com.ebc.randomsouth

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface randomsouthAPI {

    //obtener info de epis gral
    @GET("episodes")
    suspend fun getEpisodes(): List<Episode>


    //obtener info de epis
    @GET("episodes/{id}")
    suspend fun getEpisode(@Path("id") id: Int): Episode
}

class Episode {
    //instancia retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://spapi.dev/api/episodes/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()



}


