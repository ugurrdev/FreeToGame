package com.example.freetogame.presentation.navigation

sealed class Screen(val route: String) {
    object GameList : Screen("game_list")
    object GameDetail : Screen("game_detail/{gameId}") {
        fun passId(id: Int): String {
            return "game_detail/$id"
        }
    }
}
