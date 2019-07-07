package com.dmitry_kuzmin.ratesapp.rates.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmitry_kuzmin.ratesapp.R
import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rate.*

private typealias onStockClick = (stock: Stock) -> Unit

class RateListAdapter(private val onStockClick: onStockClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = arrayListOf<Stock>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rate, parent, false)
        return StockViewHolder(
            view,
            onStockClick
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StockViewHolder) {
            holder.bind(items[position])
        } else {
            throw RuntimeException("Wrong holder")
        }
    }

    fun updateItems(items: List<Stock>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private class StockViewHolder(view: View, private val onStockClick: onStockClick) :
        RecyclerView.ViewHolder(view),
        LayoutContainer {

        override val containerView: View?
            get() = itemView

        @SuppressLint("SetTextI18n")
        fun bind(stock: Stock) {
            itemView.setOnClickListener {
                onStockClick(stock)
            }
            symbolTextView.text = stock.symbol
            nameTextView.text = stock.companyName
            priceTextView.text = "${itemView.context.getString(R.string.price)}: ${stock.price}"
            rangeTextView.text = "${itemView.context.getString(R.string.range)}: ${stock.rangeLow} - ${stock.rangeHigh}"
        }
    }
}