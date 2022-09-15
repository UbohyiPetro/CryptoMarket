package com.example.cryptomarket.ui.wallet

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptomarket.R
import com.example.cryptomarket.ui.wallet.model.WalletCoinItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.wallet_coin_item.*
import kotlinx.android.synthetic.main.wallet_fragment.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WalletFragment : Fragment(R.layout.wallet_fragment) {

    private val walletViewModel: WalletViewModel by viewModels()
    private val walletAdapter: WalletAdapter = WalletAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        lifecycleScope.launch {
            walletViewModel.walletViewState.collect { viewState ->
                when {
                    viewState.isLoading -> showLoading()
                    viewState.walletCoins.isNotEmpty() -> showLoaded(viewState.walletCoins)
                    !viewState.isLoading && viewState.walletCoins.isEmpty() -> showError()
                }
            }
        }
    }

    private fun showError() {
        tvCapital.isVisible = true
        tvTodayPNL.isVisible = false
        tvTodayPNLInterest.isVisible = false
        tvUserCapital.isVisible = false
        tvUserDollarCapital.isVisible = false
        rvWalletCoins.isVisible = false
        pbWalletCoinsLoading.isVisible = false
    }

    private fun showLoaded(walletCoins: List<WalletCoinItem>) {
        pbWalletCoinsLoading.isVisible = false
        tvCapital.isVisible = true
        tvTodayPNL.isVisible = true
        tvTodayPNLInterest.isVisible = true
        tvUserCapital.isVisible = true
        tvUserDollarCapital.isVisible = true
        rvWalletCoins.isVisible = true
        walletAdapter.submitList(walletCoins)
    }

    private fun showLoading() {
        tvCapital.isVisible = false
        tvTodayPNL.isVisible = false
        tvTodayPNLInterest.isVisible = false
        tvUserCapital.isVisible = false
        tvUserDollarCapital.isVisible = false
        rvWalletCoins.isVisible = false
        pbWalletCoinsLoading.isVisible = true
    }

    private fun setupRecyclerView() {
        rvWalletCoins.apply {
            adapter = walletAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}