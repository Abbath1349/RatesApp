package com.dmitry_kuzmin.ratesapp.rates.data.data_source

import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockListTypes
import io.reactivex.Single

interface IStocksDataSource {

    fun loadStocks(types: List<StockListTypes>): Single<List<Stock>>
}