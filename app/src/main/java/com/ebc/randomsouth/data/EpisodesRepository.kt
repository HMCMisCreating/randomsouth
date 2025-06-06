package com.ebc.randomsouth.data

import com.ebc.randomsouth.controller.EpisodesAPIService
import com.ebc.randomsouth.models.Episode

interface EpisodesRepository {
    //aca pondria guardar anfibio o modificar anfinbio si tuviera otro servicio
    //pero solo tendre el GET
    suspend fun getEpisode(): List<Episode>
}

class DefaultEpisodeRepository(
    private val episodesAPIService: EpisodesAPIService
) : EpisodesRepository {
    override suspend fun getEpisode(): List<Episode> {
        return episodesAPIService.getEpisode()
    }

}