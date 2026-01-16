package com.example.freetogame.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GameDto(
    val id: Int,
    val title: String,
    val thumbnail: String,
    @SerializedName("short_description") val shortDescription: String,
    val genre: String,
    @SerializedName("game_url") val gameUrl: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("freetogame_profile_url") val profileUrl: String
)
