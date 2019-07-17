package com.dmitry_kuzmin.ratesapp.rates.data.api

import com.dmitry_kuzmin.ratesapp.rates.data.IEXStockModelDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface IEXCloudApi {

    @GET("stock/market/list/{type}")
    fun loadStocks(@Path("type") type: String): Observable<List<IEXStockModelDto>>
}