package com.example.cryptomarket.repository.api.model.coins

import com.example.cryptomarket.ui.coins_list.model.CoinItem

data class Coin(
    val `24hVolume`: String,
    val btcPrice: String,
    val change: String,
    val coinrankingUrl: String,
    val color: String,
    val iconUrl: String,
    val listedAt: Int,
    val lowVolume: Boolean,
    val marketCap: String,
    val name: String,
    val price: String,
    val rank: Int,
    val sparkline: List<String>,
    val symbol: String,
    val tier: Int,
    val uuid: String
)

fun Coin.toCoinItem(): CoinItem {
    return CoinItem(
        uuid = uuid,
        symbol = symbol,
        name = name,
        price = price.toFloat(),
        iconUrl = iconUrl,
        change = change.toFloat(),
    )
}