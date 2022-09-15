package com.example.cryptomarket.repository

import android.util.Log
import com.example.cryptomarket.repository.api.CoinApi
import com.example.cryptomarket.repository.api.model.coinDetails.toCoinDetailsItem
import com.example.cryptomarket.repository.api.model.coins.toCoinItem
import com.example.cryptomarket.repository.database.CoinDao
import com.example.cryptomarket.repository.database.model.CoinEntity
import com.example.cryptomarket.ui.wallet_coin_details.model.WalletCoinDetailsItem
import com.example.cryptomarket.ui.market.model.MarketCoinItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val API_KEY = "coinranking3ac517ca7ab117b9224a8a6fe33335355b97ea7191701afc"

class CoinRepository @Inject constructor(
    private val coinApi: CoinApi,
    private val coinDao: CoinDao,
) {
    suspend fun getCoins(): List<MarketCoinItem> {
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

    suspend fun getCoin(uuid: String): WalletCoinDetailsItem? {
        return try {
            val response = coinApi.getCoin(API_KEY = API_KEY, uuid = uuid)
            return response.body()?.data?.coin?.toCoinDetailsItem()
        } catch (ex: Exception) {
            Log.d("GET COIN", ex.toString())
            null
        }
    }

    fun observeWalletCoin(): Flow<List<CoinEntity>> = coinDao.observeUserWalletCoins()

    suspend fun addCoinToUserWallet(coin: CoinEntity) = coinDao.addCoinToUserWallet(coin)

    suspend fun updateUserWalletCoin(coin: CoinEntity) = coinDao.updateUserWalletCoin(coin)

}