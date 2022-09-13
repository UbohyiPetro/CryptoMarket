package com.example.cryptomarket.repository

import android.util.Log
import com.example.cryptomarket.repository.api.CoinApi
import com.example.cryptomarket.repository.api.model.coinDetails.toCoinDetailsItem
import com.example.cryptomarket.repository.api.model.coins.toCoinItem
import com.example.cryptomarket.ui.coin_details.model.CoinDetailsItem
import com.example.cryptomarket.ui.coins_list.model.CoinItem
import javax.inject.Inject

private const val API_KEY = "coinranking3ac517ca7ab117b9224a8a6fe33335355b97ea7191701afc"

class CoinRepository @Inject constructor(
    private val coinApi: CoinApi
) {
    suspend fun getCoins(): List<CoinItem> {
        return try {
            val response = coinApi.getCoins(API_KEY)
            val result = response.body()?.data?.coins?.map { coin ->
                coin.toCoinItem()
            }
            result ?: emptyList()
        } catch (ex: Exception) {
            Log.d("GET COINS", ex.toString())
            emptyList()
        }
    }

    suspend fun getCoin(uuid: String): CoinDetailsItem? {
        return try {
            val response = coinApi.getCoin(API_KEY = API_KEY, uuid = uuid)
            val responseMsg = response.message()
            return response.body()?.data?.coin?.toCoinDetailsItem()
        } catch (ex: Exception) {
            Log.d("GET COIN", ex.toString())
            CoinDetailsItem(iconUrl = "")
        }
    }

}