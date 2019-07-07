package com.dmitry_kuzmin.ratesapp.rates.presentation.presenters

import com.dmitry_kuzmin.ratesapp.rates.data.prefs.RatePrefs
import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockRepository
import com.dmitry_kuzmin.ratesapp.rates.presentation.contracts.IRatesList
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RateListPresenter : MviBasePresenter<IRatesList.View, IRatesList.ViewState>() {

    private val repository = StockRepository()
    private val ratePreferences: RatePrefs = RatePrefs()

    override fun bindIntents() {

        val observable = intent(IRatesList.View::loadIntents)
            .flatMap {
                repository.getStocks(ratePreferences.getFilter().selectedStockTypes)
                    .map { IRatesList.ViewState.DataState(it) as IRatesList.ViewState }
                    .startWith(IRatesList.ViewState.LoadingState)
                    .onErrorReturn { IRatesList.ViewState.ErrorState(it) }
                    .subscribeOn(Schedulers.io())
            }.observeOn(AndroidSchedulers.mainThread())


        subscribeViewState(observable, IRatesList.View::render)
    }
}