package com.example.freetogame.domain.model

data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val shortDescription: String,
    val genre: String
)
