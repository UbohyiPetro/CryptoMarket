package com.example.cryptomarket.ui.market

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptomarket.repository.CoinRepository
import com.example.cryptomarket.ui.market.model.MarketCoinItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CoinsViewState(
    val marketCoins: List<MarketCoinItem> = emptyList(),
    val isLoading: Boolean = true,
)

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private val _coinsViewState = MutableLiveData(CoinsViewState())
    val coinsViewState: LiveData<CoinsViewState>
        get() = _coinsViewState

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            val coins = coinRepository.getCoins()
            _coinsViewState.value = CoinsViewState(marketCoins = coins, isLoading = false)
        }
    }

    fun pullToRefresh() {
        getCoins()
    }
}