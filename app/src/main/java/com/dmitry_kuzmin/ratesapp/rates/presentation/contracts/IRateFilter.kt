package com.dmitry_kuzmin.ratesapp.rates.presentation.contracts

import com.dmitry_kuzmin.ratesapp.rates.domain.model.RateFilter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface IRateFilter {

    interface View : MvpView {

        fun loadIntents(): Observable<String>

        fun render(viewState: ViewState)
    }

    sealed class ViewState {
        data class DataState(val rateFilter: RateFilter) : ViewState()
        data class ErrorState(val error: Throwable) : ViewState()
    }
}