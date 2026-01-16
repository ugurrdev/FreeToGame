package com.example.freetogame.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freetogame.domain.model.Game
import com.example.freetogame.domain.usecase.GetGamesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameListViewModel(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private var allGames = listOf<Game>()
    private val _gameState = MutableStateFlow<List<Game>>(emptyList())
    val gameState: StateFlow<List<Game>> = _gameState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    init {
        fetchGames()
    }

    private fun fetchGames() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                allGames = getGamesUseCase()
                _gameState.value = allGames
            } catch (e: Exception) {
                // Hata yönetimi burada yapılabilir
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        _gameState.value = if (text.isEmpty()) {
            allGames
        } else {
            allGames.filter { it.title.contains(text, ignoreCase = true) }
        }
    }
}
