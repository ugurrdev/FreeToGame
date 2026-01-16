package com.example.freetogame.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: FreeToGameApi by lazy {
        Retrofit.Builder()
            .baseUrl(FreeToGameApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FreeToGameApi::class.java)
    }
}
