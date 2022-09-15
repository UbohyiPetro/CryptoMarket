package com.example.cryptomarket.repository.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class CoinEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
)