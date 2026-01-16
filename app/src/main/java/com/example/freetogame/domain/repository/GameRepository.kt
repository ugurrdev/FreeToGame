package com.example.freetogame.domain.repository

import com.example.freetogame.domain.model.Game
import com.example.freetogame.domain.model.GameDetail

interface GameRepository {
    suspend fun getGames(): List<Game>
    suspend fun getGameDetail(id: Int): GameDetail
}
