package com.example.cryptomarket.repository.database.di

import android.content.Context
import androidx.room.Room
import com.example.cryptomarket.repository.database.CoinDao
import com.example.cryptomarket.repository.database.CoinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DBModule {

    @Singleton
    @Provides
    fun provideCoinDao(coinDatabase: CoinDatabase): CoinDao = coinDatabase.getCoinDao()

    @Singleton
    @Provides
    fun provideCoinDatabase(@ApplicationContext context: Context): CoinDatabase =
        Room.databaseBuilder(
            context,
            CoinDatabase::class.java,
            "coins_db.db"
        ).build()
}