package com.ebc.randomsouthpark.data

import com.ebc.randomsouthpark.controller.RetrofitClient
import com.ebc.randomsouthpark.models.EpisodeData



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