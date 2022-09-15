package com.example.cryptomarket.ui.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptomarket.repository.CoinRepository
import com.example.cryptomarket.repository.database.model.toWalletCoinItem
import com.example.cryptomarket.ui.wallet.model.WalletCoinItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WalletViewState(
    val walletCoinLists: List<WalletCoinItem> = emptyList(),
    val isLoading: Boolean = true,
)

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val coinRepository: CoinRepository,
) : ViewModel() {

    private val _walletViewState = MutableStateFlow(WalletViewState())
    val walletViewState: StateFlow<WalletViewState> = _walletViewState


    init {
        viewModelScope.launch {
            coinRepository.observeWalletCoin().collect { coins ->
                WalletViewState(
                    walletCoinLists = coins.map { coinEntity ->
                        coinEntity.toWalletCoinItem()
                    },
                    isLoading = false,
                ).also { _walletViewState.value = it }
            }
        }
    }
}