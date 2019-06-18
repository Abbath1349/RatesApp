package com.dmitry_kuzmin.ratesapp.rates.presentation.ui.fragment

import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import io.reactivex.Single

interface IStockRepository {

    fun getStocks(): Single<List<Stock>>
}