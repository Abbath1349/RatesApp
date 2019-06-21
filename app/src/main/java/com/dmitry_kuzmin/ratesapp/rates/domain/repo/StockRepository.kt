package com.dmitry_kuzmin.ratesapp.rates.domain.repo

import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import com.dmitry_kuzmin.ratesapp.rates.data.data_source.IEXDataSource
import io.reactivex.Observable

class StockRepository : IStockRepository {

    private val dataSource = IEXDataSource()

    override fun getStocks(types: List<StockListTypes>): Observable<List<Stock>> {
        return dataSource.loadStocks(types)
    }
}