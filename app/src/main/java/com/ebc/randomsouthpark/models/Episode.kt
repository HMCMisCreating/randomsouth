package com.ebc.randomsouthpark.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual

@Serializable
data class ApiResponse(
    @Contextual val data: EpisodeData
)

@Serializable
data class EpisodeData(
    val id: Int,
    val name: String,
    val season: Int,
    val episode: Int,
    @SerialName ("air_date") val airDate: String?,
    @SerialName ("wiki_url") val wikiSrc: String?,
    @SerialName ("thumbnail_url") val imgSrc: String?,
    val description: String?,
    @SerialName ("created_at") val created: String?,
    @SerialName ("updated_at") val updated:  String?,
    val characters: List<String>?,
    val locations: List<String>?
)

