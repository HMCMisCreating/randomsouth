package com.ebc.randomsouth.controller

import com.ebc.randomsouth.models.Episode
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesAPIService {

    @GET("episode")
    suspend fun getEpisode(): List<Episode>

    //obtener info de epis
    @GET("episodes/{id}")
    suspend fun getEpisode(@Path("id") id: Int): Episode

}
 /*
class Episode{
suspend fun getEpisodes(): List<com.ebc.randomsouth.Episode> {
    return EpisodesAPIService.getEpisode()
}

fun getEpisode(id: Int): com.ebc.randomsouth.Episode {
    return EpisodesAPIService.getEpisode(id)
}
}
*/
