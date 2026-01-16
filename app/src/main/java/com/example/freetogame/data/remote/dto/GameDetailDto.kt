package com.example.freetogame.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GameDetailDto(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val description: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("game_url") val gameUrl: String,
    @SerializedName("minimum_system_requirements") val minimumSystemRequirements: SystemRequirementsDto?,
    val screenshots: List<ScreenshotDto>
)

data class SystemRequirementsDto(
    val os: String?,
    val processor: String?,
    val memory: String?,
    val graphics: String?,
    val storage: String?
)

data class ScreenshotDto(
    val id: Int,
    val image: String
)
