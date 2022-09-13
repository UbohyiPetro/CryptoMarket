package com.example.cryptomarket.ui.coin_details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.cryptomarket.R
import com.example.cryptomarket.ui.coin_details.model.CoinDetailsItem
import com.example.cryptomarket.ui.coins_list.CoinsViewState
import com.example.cryptomarket.ui.coins_list.fromSVGtoPNG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.coin_details_fragment.*

@AndroidEntryPoint
class CoinDetailsFragment : Fragment(R.layout.coin_details_fragment) {

    private val coinDetailsViewModel: CoinDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coinDetailsViewStateObserver = Observer<CoinDetailsViewState> { viewState ->
            when {
                viewState.isLoading -> showLoading()
                viewState.coin != null -> showLoaded(viewState.coin)
            }
        }
        coinDetailsViewModel.coinDetailsViewState.observe(
            viewLifecycleOwner,
            coinDetailsViewStateObserver
        )
    }

    private fun showLoaded(coin: CoinDetailsItem) {
        pbCoinDetailsLoading.isVisible = false
        showAllForLoaded()
        Glide.with(this).load(coin.iconUrl.fromSVGtoPNG()).fitCenter().into(ivCoinDetailsIcon)
        tvCoinDetailsSymbol.text = coin.symbol
    }

    private fun showLoading() {
        hideAllForLoading()
        pbCoinDetailsLoading.isVisible = true
    }

    private fun hideAllForLoading() {
        tvBalance.isVisible = false
        tvCoinBalance.isVisible = false
        tvCoinBalanceDollarPrice.isVisible = false
        tvCoinDetailsSymbol.isVisible = false
        ivCoinDetailsIcon.isVisible = false
    }

    private fun showAllForLoaded() {
        tvBalance.isVisible = true
        tvCoinBalance.isVisible = true
        tvCoinBalanceDollarPrice.isVisible = true
        tvCoinDetailsSymbol.isVisible = true
        ivCoinDetailsIcon.isVisible = true
    }
}