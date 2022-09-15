package com.example.cryptomarket.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptomarket.repository.database.model.CoinEntity

@Database(
    entities = [CoinEntity::class],
    version = 1
)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun getCoinDao(): CoinDao
}