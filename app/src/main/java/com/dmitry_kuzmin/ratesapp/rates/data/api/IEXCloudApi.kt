package com.dmitry_kuzmin.ratesapp.rates.data.api

import com.dmitry_kuzmin.ratesapp.rates.data.IEXStockModel
import io.reactivex.Single


interface IEXCloudApi {

    fun loadStocks(type: String): Single<List<IEXStockModel>>
}