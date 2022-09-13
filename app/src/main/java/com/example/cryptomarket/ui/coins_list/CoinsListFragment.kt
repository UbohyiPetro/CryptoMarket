package com.example.cryptomarket.ui.coins_list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.cryptomarket.R
import com.example.cryptomarket.ui.coins_list.model.CoinItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.coins_list_fragment.*

@AndroidEntryPoint
class CoinsListFragment : Fragment(R.layout.coins_list_fragment) {

    private val coinsViewModel: CoinsViewModel by viewModels()
    private val coinsListAdapter: CoinsListAdapter = CoinsListAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        val pullToRefresh: SwipeRefreshLayout = pullToRefresh
        pullToRefresh.setOnRefreshListener {
            coinsViewModel.pullToRefresh()
        }
        val coinsViewStateObserver = Observer<CoinsViewState> { viewState ->
            when {
                viewState.isLoading -> showLoading()
                viewState.coins.isNotEmpty() -> {
                    showLoaded(viewState.coins)
                    pullToRefresh.isRefreshing = false
                }
            }
        }
        coinsViewModel.coinsViewState.observe(viewLifecycleOwner, coinsViewStateObserver)
    }

    private fun setupRecyclerView() {
        rvCoins.apply {
            adapter = coinsListAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showLoaded(coinsList: List<CoinItem>) {
        pbCoinsLoading.isVisible = false
        rvCoins.isVisible = true
        coinsListAdapter.submitList(coinsList)
    }

    private fun showLoading() {
        rvCoins.isVisible = false
        pbCoinsLoading.isVisible = true
    }
}