package com.example.cryptomarket.ui.coins_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptomarket.R
import com.example.cryptomarket.ui.model.CoinItem
import kotlinx.android.synthetic.main.coin_item.view.*


class CoinsListAdapter : ListAdapter<CoinItem, CoinsListAdapter.CoinsViewHolder>(
    object : DiffUtil.ItemCallback<CoinItem>() {
        override fun areItemsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
            return oldItem.price == newItem.price
        }

        override fun areContentsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
            return oldItem == newItem
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
        val oldIconUrl = coin.iconUrl
        var newIconUrl = ""
        val png: CharSequence = "png"
        oldIconUrl.replaceRange(oldIconUrl.length - 3, oldIconUrl.length, png)
            .also { newIconUrl = it }
        holder.itemView.apply {
            Glide.with(this).load(newIconUrl).fitCenter().into(ivCoinIcon)
            tvCoinSymbol.text = coin.symbol
            tvCoinName.text = coin.name
            when {
                coin.change < 0 -> tvCoinChange.setTextColor(resources.getColor(R.color.red))
                coin.change > 0 -> tvCoinChange.setTextColor(resources.getColor(R.color.green))
                else -> tvCoinChange.setTextColor(resources.getColor(R.color.white))
            }
            tvCoinChange.text = coin.change.toString()
            tvCoinPrice.text = coin.price.toString()
        }
    }

}