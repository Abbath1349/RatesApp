package com.dmitry_kuzmin.ratesapp.menu.domain.model

import com.dmitry_kuzmin.ratesapp.core.domain.model.Money

data class Stock(
    val symbol: String?,
    val companyName: String,
    val price: Money,
    val rangeLow: Money,
    val rangeHigh: Money
)