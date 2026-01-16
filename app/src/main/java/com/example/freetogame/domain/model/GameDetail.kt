package com.example.freetogame.domain.model

data class GameDetail(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val description: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val developer: String,
    val releaseDate: String,
    val gameUrl: String,
    val minimumSystemRequirements: SystemRequirements?,
    val screenshots: List<Screenshot>
)

data class SystemRequirements(
    val os: String?,
    val processor: String?,
    val memory: String?,
    val graphics: String?,
    val storage: String?
)

data class Screenshot(
    val id: Int,
    val image: String
)
