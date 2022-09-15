package com.example.cryptomarket.repository.api.model.coinDetails

import com.example.cryptomarket.ui.wallet_coin_details.model.WalletCoinDetailsItem

data class CoinDetails(
    val `24hVolume`: String,
    val allTimeHigh: AllTimeHigh,
    val btcPrice: String,
    val change: String,
    val coinrankingUrl: String,
    val color: String,
    val description: String,
    val fullyDilutedMarketCap: String,
    val iconUrl: String,
    val links: List<Link>,
    val listedAt: Int,
    val marketCap: String,
    val name: String,
    val numberOfExchanges: Int,
    val numberOfMarkets: Int,
    val price: String,
    val priceAt: Int,
    val rank: Int,
    val sparkline: List<String>,
    val supply: Supply,
    val symbol: String,
    val uuid: String,
    val websiteUrl: String
)

fun CoinDetails.toCoinDetailsItem(): WalletCoinDetailsItem {
    return WalletCoinDetailsItem(
        iconUrl = iconUrl,
        symbol = symbol,
    )
}


