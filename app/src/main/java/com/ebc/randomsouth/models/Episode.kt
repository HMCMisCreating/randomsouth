package com.ebc.randomsouth.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
//tiene que ser iwal a lo que traiga la api en su llamada!! sino traera problemasss
data class Episode(
    val id: Int,
    val name: String,
    val season: Int,
    val episode: Int,
    @SerialName ("thumbnail_url") val imgSrc: String,
    @SerialName ("air_date") val airDate: String,
    @SerialName ("wiki_url") val wikiSrc: String,
    val description: String,
    @SerialName ("created_at") val created: String,
    @SerialName ("updated_a") val updated: String,
    val characters: List<String>,
    val locations: List<String>
)

// @SerialName para cambiar el nombre de un coso que lo necesite, la propiedad de json sea distinta
//al nombre de la variable real

