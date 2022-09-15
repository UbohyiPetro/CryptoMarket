package com.example.cryptomarket.ui.wallet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptomarket.R
import com.example.cryptomarket.ui.wallet.model.WalletCoinItem

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
                R.layout.coin_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        val coin = currentList[position]
//        holder.itemView.apply {
//            setOnClickListener {
//                /*findNavController().navigate(
//                    CoinsListFragmentDirections.actionCoinsListFragmentToCoinDetailsFragment(
//                        coin.uuid
//                    )
//                )*/
//            }
//            Glide.with(this).load(coin.iconUrl.fromSVGtoPNG()).fitCenter().into(ivCoinIcon)
//            tvCoinSymbol.text = coin.symbol
//            tvCoinName.text = coin.name
//            when {
//                coin.change < 0 -> tvCoinChange.setTextColor(resources.getColor(R.color.red))
//                coin.change > 0 -> tvCoinChange.setTextColor(resources.getColor(R.color.green))
//                else -> tvCoinChange.setTextColor(resources.getColor(R.color.white))
//            }
//            tvCoinChange.text = coin.change.toString()
//            var coinPrice = coin.price.toString()
//            if (coinPrice.contains('E')) {
//                val n = BigDecimal(coinPrice)
//                coinPrice = n.toPlainString()
//            }
//            tvCoinPrice.text = coinPrice
//        }
    }
}

