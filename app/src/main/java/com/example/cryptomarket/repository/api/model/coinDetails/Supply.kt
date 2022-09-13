package com.example.cryptomarket.repository.api.model.coinDetails

data class Supply(
    val circulating: String,
    val confirmed: Boolean,
    val max: String,
    val sypplyAt: Int,
    val total: String
)