package com.dmitry_kuzmin.ratesapp.rates.domain.repo

import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import io.reactivex.Single

interface IStockRepository {

    fun getStocks(types: List<StockListTypes>): Single<List<Stock>>
}