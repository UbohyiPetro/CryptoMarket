package com.example.cryptomarket.ui.coins_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptomarket.R
import com.example.cryptomarket.helper.extensions.fromSVGtoPNG
import com.example.cryptomarket.ui.coins_list.model.MarketCoinItem
import kotlinx.android.synthetic.main.coin_item.view.*
import java.math.BigDecimal


class MarketAdapter : ListAdapter<MarketCoinItem, MarketAdapter.CoinsViewHolder>(
    object : DiffUtil.ItemCallback<MarketCoinItem>() {
        override fun areItemsTheSame(oldMarketCoinItem: MarketCoinItem, newMarketCoinItem: MarketCoinItem): Boolean {
            return oldMarketCoinItem.price == newMarketCoinItem.price
        }

        override fun areContentsTheSame(oldMarketCoinItem: MarketCoinItem, newMarketCoinItem: MarketCoinItem): Boolean {
            return oldMarketCoinItem == newMarketCoinItem
        }

    }
) {

    inner class CoinsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        return CoinsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.coin_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val coin = currentList[position]
        holder.itemView.apply {
            setOnClickListener {
                /*findNavController().navigate(
                    CoinsListFragmentDirections.actionCoinsListFragmentToCoinDetailsFragment(
                        coin.uuid
                    )
                )*/
            }
            Glide.with(this).load(coin.iconUrl.fromSVGtoPNG()).fitCenter().into(ivCoinIcon)
            tvCoinSymbol.text = coin.symbol
            tvCoinName.text = coin.name
            when {
                coin.change < 0 -> tvCoinChange.setTextColor(resources.getColor(R.color.red))
                coin.change > 0 -> tvCoinChange.setTextColor(resources.getColor(R.color.green))
                else -> tvCoinChange.setTextColor(resources.getColor(R.color.white))
            }
            tvCoinChange.text = coin.change.toString()
            var coinPrice = coin.price.toString()
            if (coinPrice.contains('E')) {
                val n = BigDecimal(coinPrice)
                coinPrice = n.toPlainString()
            }
            tvCoinPrice.text = coinPrice
        }
    }
}

