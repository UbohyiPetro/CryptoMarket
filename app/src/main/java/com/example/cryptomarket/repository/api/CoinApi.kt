package com.example.cryptomarket.repository.api

import com.example.cryptomarket.repository.api.model.coinDetails.CoinDetailsResponse
import com.example.cryptomarket.repository.api.model.coins.CoinResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface CoinApi {

    @GET("coins")
    suspend fun getCoins(@Header("x-access-token") API_KEY: String): Response<CoinResponse>

    @GET("coin/{uuid}")
    suspend fun getCoin(
        @Header("x-access-token") API_KEY: String,
        @Path("uuid") uuid: String,
    ): Response<CoinDetailsResponse>
}