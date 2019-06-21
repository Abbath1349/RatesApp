package com.dmitry_kuzmin.ratesapp.rates.data.data_source

import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockListTypes
import io.reactivex.Observable

interface IStocksDataSource {

    fun loadStocks(types: List<StockListTypes>): Observable<List<Stock>>
}