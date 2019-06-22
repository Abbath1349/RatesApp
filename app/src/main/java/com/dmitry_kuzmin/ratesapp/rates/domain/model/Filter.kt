package com.dmitry_kuzmin.ratesapp.rates.domain.model

import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockListTypes

data class Filter(val selectedStockTypes: List<StockListTypes>)