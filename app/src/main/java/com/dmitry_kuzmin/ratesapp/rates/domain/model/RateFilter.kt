package com.dmitry_kuzmin.ratesapp.rates.domain.model

import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockListTypes

data class RateFilter(val selectedStockTypes: List<StockListTypes>)