package com.dmitry_kuzmin.ratesapp.rates.presentation.model

import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockListTypes

data class StockTypeItemPM(val stockType: StockListTypes, var isSelected: Boolean)