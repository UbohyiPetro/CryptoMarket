package com.example.cryptomarket.repository.api

import com.example.cryptomarket.repository.api.model.CoinResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface CoinApi {

    @GET("coins")
    suspend fun getCoins(@Header("x-access-token") API_KEY: String): Response<CoinResponse>
}