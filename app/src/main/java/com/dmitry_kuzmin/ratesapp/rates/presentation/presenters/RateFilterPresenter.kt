package com.dmitry_kuzmin.ratesapp.rates.presentation.presenters

import com.dmitry_kuzmin.ratesapp.rates.data.prefs.RatePrefs
import com.dmitry_kuzmin.ratesapp.rates.domain.model.RateFilter
import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockListTypes
import com.dmitry_kuzmin.ratesapp.rates.presentation.contracts.IRateFilter
import com.dmitry_kuzmin.ratesapp.rates.presentation.model.RateFilterPM
import com.dmitry_kuzmin.ratesapp.rates.presentation.model.StockTypeItemPM
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RateFilterPresenter : MviBasePresenter<IRateFilter.View, IRateFilter.ViewState>() {

    private val ratePrefs = RatePrefs()

    override fun bindIntents() {

        val loadScreen = intent(IRateFilter.View::loadIntents)
            .flatMap {
                Observable.just(ratePrefs.getFilter())
                    .map { mapRateFilter(it) }
                    .map { IRateFilter.ViewState.DataState(it) as IRateFilter.ViewState }
                    .onErrorReturn { IRateFilter.ViewState.ErrorState(it) }
                    .subscribeOn(Schedulers.io())
            }

        val saveAndExit = intent(IRateFilter.View::onSaveClick)
            .flatMap {
                Observable.fromCallable { ratePrefs.getFilter().copy(selectedStockTypes = it) }
                    .map { IRateFilter.ViewState.ExitState }
                    .subscribeOn(Schedulers.io())
            }

        val allIntents = Observable.merge(loadScreen, saveAndExit).observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(allIntents, IRateFilter.View::render)
    }

    private fun mapRateFilter(rateFilter: RateFilter): RateFilterPM {
        val items = StockListTypes.values().map { StockTypeItemPM(it, rateFilter.selectedStockTypes.contains(it)) }
        return RateFilterPM(items)
    }
}