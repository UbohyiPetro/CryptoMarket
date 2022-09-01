package com.example.cryptomarket

import androidx.lifecycle.ViewModel
import com.example.cryptomarket.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    suspend fun getCoins(): String = coinRepository.getCoins()
}