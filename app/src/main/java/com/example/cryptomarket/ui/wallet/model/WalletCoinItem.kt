package com.example.cryptomarket.ui.wallet.model

data class WalletCoinItem(
    val uuid: String,
    val symbol: String,
    val name: String,
    val iconUrl: String,
    val assetValue: Float,
    val numberOfCoin: Float,
)