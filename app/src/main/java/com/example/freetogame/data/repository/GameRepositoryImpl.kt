package com.example.freetogame.data.repository

import com.example.freetogame.data.mapper.toGame
import com.example.freetogame.data.mapper.toGameDetail
import com.example.freetogame.data.remote.FreeToGameApi
import com.example.freetogame.domain.model.Game
import com.example.freetogame.domain.model.GameDetail
import com.example.freetogame.domain.repository.GameRepository

class GameRepositoryImpl(
    private val api: FreeToGameApi
) : GameRepository {

    override suspend fun getGames(): List<Game> {
        return api.getGames().map { it.toGame() }
    }

    override suspend fun getGameDetail(id: Int): GameDetail {
        return api.getGameDetail(id).toGameDetail()
    }
}
