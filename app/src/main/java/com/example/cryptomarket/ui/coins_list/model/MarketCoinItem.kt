package com.example.cryptomarket.ui.coins_list.model

data class MarketCoinItem(
    val uuid: String,
    val symbol: String,
    val name: String,
    val iconUrl: String,
    val price: Float,
    val change: Float,
)