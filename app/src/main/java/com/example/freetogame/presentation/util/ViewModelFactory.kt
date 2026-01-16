package com.example.freetogame.presentation.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.freetogame.data.remote.RetrofitInstance
import com.example.freetogame.data.repository.GameRepositoryImpl
import com.example.freetogame.domain.usecase.GetGameDetailUseCase
import com.example.freetogame.domain.usecase.GetGamesUseCase
import com.example.freetogame.presentation.screens.detail.GameDetailViewModel
import com.example.freetogame.presentation.screens.list.GameListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {
    
    private val api = RetrofitInstance.api
    private val repository = GameRepositoryImpl(api)
    
    private val getGamesUseCase = GetGamesUseCase(repository)
    private val getGameDetailUseCase = GetGameDetailUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(GameListViewModel::class.java) -> {
                GameListViewModel(getGamesUseCase) as T
            }
            modelClass.isAssignableFrom(GameDetailViewModel::class.java) -> {
                GameDetailViewModel(getGameDetailUseCase) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
