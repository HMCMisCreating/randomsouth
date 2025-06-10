package com.ebc.randomsouth.data

import com.ebc.randomsouth.controller.RetrofitClient
import com.ebc.randomsouth.models.EpisodeData



class EpisodeRepository {
    suspend fun getRandomEpisode(): EpisodeData? {
        return try {
            val randomId = (1..314).random()
            val response = RetrofitClient.episodeApiService.getEpisodeById(randomId)

            if (response.isSuccessful) {
                response.body()?.data // Extract the BLOODY nested data
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}