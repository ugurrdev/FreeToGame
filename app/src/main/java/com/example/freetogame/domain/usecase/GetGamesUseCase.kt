package com.example.freetogame.domain.usecase

import com.example.freetogame.domain.model.Game
import com.example.freetogame.domain.repository.GameRepository

class GetGamesUseCase(
    private val repository: GameRepository
) {
    suspend operator fun invoke(): List<Game> {
        return repository.getGames()
    }
}
