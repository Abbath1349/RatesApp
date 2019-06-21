package com.dmitry_kuzmin.ratesapp.rates.domain.repo

import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import io.reactivex.Observable

interface IStockRepository {

    fun getStocks(types: List<StockListTypes>): Observable<List<Stock>>
}