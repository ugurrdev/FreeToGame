package com.example.freetogame.data.remote

import com.example.freetogame.data.remote.dto.GameDetailDto
import com.example.freetogame.data.remote.dto.GameDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeToGameApi {
    @GET("api/games")
    suspend fun getGames(): List<GameDto>

    @GET("api/game")
    suspend fun getGameDetail(@Query("id") id: Int): GameDetailDto

    companion object {
        const val BASE_URL = "https://www.freetogame.com/"
    }
}
