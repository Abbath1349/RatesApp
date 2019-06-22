package com.dmitry_kuzmin.ratesapp.rates.data.data_source

import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import com.dmitry_kuzmin.ratesapp.rates.data.api.IEXCloudApi
import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockListTypes
import com.dmitry_kuzmin.ratesapp.settings.data.prefs.IEXTokenProvider
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val ROOT_URL = "https://cloud.iexapis.com/stable/"

class IEXDataSource : IStocksDataSource {

    private val tokenProvider = IEXTokenProvider()

    private val api by lazy {
        retrofit.create(IEXCloudApi::class.java)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ROOT_URL)
            .client(createIntereptor())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createIntereptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalUrl = original.url()

                val url = originalUrl.newBuilder()
                    .addQueryParameter("token", tokenProvider.getToken())
                    .build()

                val request = original.newBuilder().url(url).build()

                chain.proceed(request)
            }.build()
    }

    override fun loadStocks(types: List<StockListTypes>): Observable<List<Stock>> {
        return if (types.size == 1 && types.none { it == StockListTypes.ALL }) {
            load(types.single())
        } else if (types.contains(StockListTypes.ALL)) {
            loadAll(StockListTypes.values().filter { it != StockListTypes.ALL })
        } else {
            loadAll(types)
        }
    }

    private fun loadAll(types: List<StockListTypes>): Observable<List<Stock>> {
        return Observable.fromIterable(types)
            .flatMap { load(it) }
            .reduce { a: List<Stock>, b: List<Stock> ->
                val list = arrayListOf<Stock>()
                list.addAll(a)
                list.addAll(b)
                list
            }
            .flatMapObservable { Observable.just(it.distinctBy { stock -> stock.symbol }) }
    }

    private fun load(item: StockListTypes): Observable<List<Stock>> {
        return api.loadStocks(item.convert())
            .map { list -> list.map { it.toStockModel() } }
    }

    private fun StockListTypes.convert(): String {
        return when (this) {
            StockListTypes.MOST_ACTIVE -> "mostactive"
            StockListTypes.GAINERS -> "gainers"
            StockListTypes.LOSERS -> "losers"
            StockListTypes.ALL -> ""
        }
    }
}