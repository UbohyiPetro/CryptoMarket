package com.example.cryptomarket.ui.coins_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptomarket.repository.CoinRepository
import com.example.cryptomarket.ui.model.CoinItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CoinsViewState(
    val coins: List<CoinItem>,
    val isLoading: Boolean = true,
)

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {

    private val _coinsViewState = MutableLiveData<CoinsViewState>()
    val coinsViewState: LiveData<CoinsViewState>
        get() = _coinsViewState

    init {
        viewModelScope.launch {
            val coins = coinRepository.getCoins()
            _coinsViewState.value = CoinsViewState(coins = coins, isLoading = false)
        }
    }
}