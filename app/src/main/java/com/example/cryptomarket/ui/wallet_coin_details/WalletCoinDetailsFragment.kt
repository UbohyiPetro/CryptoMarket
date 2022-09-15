package com.example.cryptomarket.ui.wallet_coin_details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.cryptomarket.R
import com.example.cryptomarket.helper.extensions.fromSVGtoPNG
import com.example.cryptomarket.ui.wallet_coin_details.model.WalletCoinDetailsItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.wallet_coin_details_fragment.*

@AndroidEntryPoint
class WalletCoinDetailsFragment : Fragment(R.layout.wallet_coin_details_fragment) {

    private val walletCoinDetailsViewModel: WalletCoinDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val coinDetailsViewStateObserver = Observer<CoinDetailsViewState> { viewState ->
            when {
                viewState.isLoading -> showLoading()
                viewState.walletCoin != null -> showLoaded(viewState.walletCoin)
            }
        }
        walletCoinDetailsViewModel.coinDetailsViewState.observe(
            viewLifecycleOwner,
            coinDetailsViewStateObserver
        )
    }

    private fun showLoaded(walletCoin: WalletCoinDetailsItem) {
        pbWalletCoinDetailsLoading.isVisible = false
        showAllForLoaded()
        Glide.with(this).load(walletCoin.iconUrl.fromSVGtoPNG()).fitCenter()
            .into(ivWalletCoinDetailsIcon)
        tvWalletCoinDetailsSymbol.text = walletCoin.symbol
    }

    private fun showLoading() {
        hideAllForLoading()
        pbWalletCoinDetailsLoading.isVisible = true
    }

    private fun hideAllForLoading() {
        tvBalance.isVisible = false
        tvWalletCoinBalance.isVisible = false
        tvWalletCoinBalanceDollarPrice.isVisible = false
        tvWalletCoinDetailsSymbol.isVisible = false
        ivWalletCoinDetailsIcon.isVisible = false
    }

    private fun showAllForLoaded() {
        tvBalance.isVisible = true
        tvWalletCoinBalance.isVisible = true
        tvWalletCoinBalanceDollarPrice.isVisible = true
        tvWalletCoinDetailsSymbol.isVisible = true
        ivWalletCoinDetailsIcon.isVisible = true
    }
}