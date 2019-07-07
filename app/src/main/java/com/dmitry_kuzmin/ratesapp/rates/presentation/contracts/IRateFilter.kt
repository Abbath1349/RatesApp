package com.dmitry_kuzmin.ratesapp.rates.presentation.contracts

import com.dmitry_kuzmin.ratesapp.rates.presentation.model.RateFilterPM
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface IRateFilter {

    interface View : MvpView {

        fun loadIntents(): Observable<String>

        fun render(viewState: ViewState)
    }

    sealed class ViewState {
        data class DataState(val rateFilter: RateFilterPM) : ViewState()
        data class ErrorState(val error: Throwable) : ViewState()
    }
}