package com.example.freetogame.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freetogame.domain.model.GameDetail
import com.example.freetogame.domain.usecase.GetGameDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameDetailViewModel(
    private val getGameDetailUseCase: GetGameDetailUseCase
) : ViewModel() {

    private val _selectedGame = MutableStateFlow<GameDetail?>(null)
    val selectedGame: StateFlow<GameDetail?> = _selectedGame.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun getGameDetails(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _selectedGame.value = null
            try {
                _selectedGame.value = getGameDetailUseCase(id)
            } catch (e: Exception) {
                // Hata yönetimi
            } finally {
                _isLoading.value = false
            }
        }
    }
}
