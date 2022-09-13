package com.example.cryptomarket.ui.coin_details

import androidx.lifecycle.*
import com.example.cryptomarket.repository.CoinRepository
import com.example.cryptomarket.ui.coin_details.model.CoinDetailsItem
import com.example.cryptomarket.ui.coins_list.CoinsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CoinDetailsViewState(
    val coin: CoinDetailsItem? = null,
    val isLoading: Boolean = true,
)


@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val coinRepository: CoinRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val uuid: String? = savedStateHandle.get<String>("uuid")

    private val _coinDetailsViewState = MutableLiveData(CoinDetailsViewState())
    val coinDetailsViewState: LiveData<CoinDetailsViewState>
        get() = _coinDetailsViewState

    init {
        viewModelScope.launch {
            if (uuid != null) {
                val coin = coinRepository.getCoin(uuid = uuid)
                _coinDetailsViewState.value = CoinDetailsViewState(coin = coin, isLoading = false)
            } else {
                _coinDetailsViewState.value = CoinDetailsViewState(coin = null, isLoading = true)
            }
        }
    }
}