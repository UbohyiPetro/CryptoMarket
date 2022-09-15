package com.example.cryptomarket.ui.wallet_coin_details

import androidx.lifecycle.*
import com.example.cryptomarket.repository.CoinRepository
import com.example.cryptomarket.ui.wallet_coin_details.model.WalletCoinDetailsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CoinDetailsViewState(
    val walletCoin: WalletCoinDetailsItem? = null,
    val isLoading: Boolean = true,
)


@HiltViewModel
class WalletCoinDetailsViewModel @Inject constructor(
    private val coinRepository: CoinRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val uuid: String? = savedStateHandle.get<String>("uuid")

    private val _coinDetailsViewState = MutableLiveData(CoinDetailsViewState())
    val coinDetailsViewState: LiveData<CoinDetailsViewState>
        get() = _coinDetailsViewState

    init {
        viewModelScope.launch {
            if (uuid != null) {
                val coin = coinRepository.getCoin(uuid = uuid)
                _coinDetailsViewState.value =
                    CoinDetailsViewState(walletCoin = coin, isLoading = false)
            } else {
                _coinDetailsViewState.value =
                    CoinDetailsViewState(walletCoin = null, isLoading = true)
            }
        }
    }
}