package com.example.cryptomarket.repository.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptomarket.ui.wallet.model.WalletCoinItem

@Entity(tableName = "coins")
data class CoinEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "uuid") val uuid: String,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "iconUrl") val iconUrl: String,
    @ColumnInfo(name = "numberOfCoins") val numberOfCoin: Float,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "coinsPrice") val assetValue: Float = numberOfCoin * price,
)

fun CoinEntity.toWalletCoinItem(): WalletCoinItem {
    return WalletCoinItem(
        uuid = uuid,
        symbol = symbol,
        name = name,
        iconUrl = iconUrl,
        assetValue = assetValue,
        numberOfCoin = numberOfCoin,
    )
}