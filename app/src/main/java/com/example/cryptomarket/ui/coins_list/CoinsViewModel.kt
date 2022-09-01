package com.example.cryptomarket.ui.coins_list

import androidx.lifecycle.ViewModel
import com.example.cryptomarket.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    suspend fun getCoins(): String = coinRepository.getCoins()
}