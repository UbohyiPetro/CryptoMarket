package com.example.cryptomarket.repository

import com.example.cryptomarket.repository.api.CoinApi
import javax.inject.Inject

private const val API_KEY = "coinranking3ac517ca7ab117b9224a8a6fe33335355b97ea7191701afc"

class CoinRepository @Inject constructor(
    private val coinApi: CoinApi
) {
    suspend fun getCoins(): String {
        val response = coinApi.getCoins(API_KEY)
        return response.body()?.data?.coins?.get(0)?.name ?: "response null"
    }
}