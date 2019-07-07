package com.dmitry_kuzmin.ratesapp.rates.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmitry_kuzmin.ratesapp.R
import com.dmitry_kuzmin.ratesapp.core.presentation.ui.BaseHolder
import com.dmitry_kuzmin.ratesapp.rates.presentation.model.StockTypeItemPM
import kotlinx.android.synthetic.main.item_stock_type.*

private typealias OnStockTypeClick = (stockType: StockTypeItemPM) -> Unit

class StockTypeAdapter(private val onFilterClick: OnStockTypeClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = arrayListOf<StockTypeItemPM>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stock_type, parent, false)
        return StockTypeViewHolder(view, onFilterClick)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StockTypeViewHolder) {
            holder.bind(items[position])
        } else {
            throw RuntimeException("Wrong holder")
        }
    }

    fun updateItems(items: List<StockTypeItemPM>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private class StockTypeViewHolder(view: View, private val onStockTypeClick: OnStockTypeClick) :
        BaseHolder(view) {

        fun bind(item: StockTypeItemPM) {
            containerView?.let { v ->
                onStockTypeClick(item)
                stockTypeToggleButton.text = v.context.getString(item.stockType.textId)
                stockTypeToggleButton.isChecked = item.isSelected
            }
        }
    }
}