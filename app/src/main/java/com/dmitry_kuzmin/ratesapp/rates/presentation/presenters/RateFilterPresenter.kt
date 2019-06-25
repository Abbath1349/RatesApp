package com.dmitry_kuzmin.ratesapp.rates.presentation.presenters

import com.dmitry_kuzmin.ratesapp.rates.data.prefs.RatePrefs
import com.dmitry_kuzmin.ratesapp.rates.presentation.contracts.IRateFilter
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RateFilterPresenter : MviBasePresenter<IRateFilter.View, IRateFilter.ViewState>() {

    private val ratePrefs = RatePrefs()

    override fun bindIntents() {

        val observable = intent(IRateFilter.View::loadIntents)
            .flatMap {
                Observable.just(ratePrefs.getFilter())
                    .map { IRateFilter.ViewState.DataState(it) as IRateFilter.ViewState }
                    .onErrorReturn { IRateFilter.ViewState.ErrorState(it) }
                    .subscribeOn(Schedulers.io())
            }.observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(observable, IRateFilter.View::render)
    }
}