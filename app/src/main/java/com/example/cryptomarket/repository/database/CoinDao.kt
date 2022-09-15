package com.example.cryptomarket.repository.database

import androidx.room.*
import com.example.cryptomarket.repository.database.model.CoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCoinToUserWallet(coin: CoinEntity)

    @Query("SELECT * FROM coins")
    fun observeUserWalletCoins(): Flow<List<CoinEntity>>

    @Update
    suspend fun updateUserWalletCoin(coin: CoinEntity)
}