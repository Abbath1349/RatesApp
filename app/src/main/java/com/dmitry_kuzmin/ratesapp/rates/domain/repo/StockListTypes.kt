package com.dmitry_kuzmin.ratesapp.rates.domain.repo

import androidx.annotation.StringRes
import com.dmitry_kuzmin.ratesapp.R

enum class StockListTypes(@StringRes val textId: Int) {
    MOST_ACTIVE(R.string.stock_type_most_active),
    GAINERS(R.string.stock_type_gainers),
    LOSERS(R.string.stock_type_losers),
    ALL(R.string.stock_type_all)
}