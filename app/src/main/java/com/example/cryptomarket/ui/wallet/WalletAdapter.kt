package com.example.cryptomarket.ui.wallet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptomarket.R
import com.example.cryptomarket.helper.extensions.fromSVGtoPNG
import com.example.cryptomarket.ui.wallet.model.WalletCoinItem
import kotlinx.android.synthetic.main.wallet_coin_item.view.*
import java.math.BigDecimal

class WalletAdapter : ListAdapter<WalletCoinItem, WalletAdapter.WalletViewHolder>(
    object : DiffUtil.ItemCallback<WalletCoinItem>() {
        override fun areItemsTheSame(
            oldMarketItem: WalletCoinItem,
            newMarketItem: WalletCoinItem
        ): Boolean {
            return oldMarketItem.assetValue == newMarketItem.assetValue
        }

        override fun areContentsTheSame(
            oldMarketItem: WalletCoinItem,
            newMarketItem: WalletCoinItem
        ): Boolean {
            return oldMarketItem == newMarketItem
        }

    }
) {

    inner class WalletViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        return WalletViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.wallet_coin_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        val coin = currentList[position]
        holder.itemView.apply {
            setOnClickListener {
                /*findNavController().navigate(
                    CoinsListFragmentDirections.actionCoinsListFragmentToCoinDetailsFragment(
                        coin.uuid
                    )
                )*/
            }
            Glide.with(this).load(coin.iconUrl.fromSVGtoPNG()).fitCenter().into(ivWalletCoinIcon)
            tvWalletCoinSymbol.text = coin.symbol
            tvWalletCoinName.text = coin.name
            var numberOfCoin = coin.numberOfCoin.toString()
            if (numberOfCoin.contains('E')) {
                val n = BigDecimal(numberOfCoin)
                numberOfCoin = n.toPlainString()
            }
            tvWalletNumberOfCoin.text = numberOfCoin
            tvWalletCoinDollarPrice.text = coin.assetValue.toString()
        }
    }
}

