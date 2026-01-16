package com.example.freetogame.domain.usecase

import com.example.freetogame.domain.model.GameDetail
import com.example.freetogame.domain.repository.GameRepository

class GetGameDetailUseCase(
    private val repository: GameRepository
) {
    suspend operator fun invoke(id: Int): GameDetail {
        return repository.getGameDetail(id)
    }
}
