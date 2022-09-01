package com.example.cryptomarket.ui.model

data class CoinItem(
    val symbol: String,
    val name: String,
    val iconUrl: String,
    val price: Float,
    val change: Float,
)