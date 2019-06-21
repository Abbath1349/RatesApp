package com.dmitry_kuzmin.ratesapp.rates.presentation.contracts

import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface IRatesList {

    interface View : MvpView {

        fun loadIntents(): Observable<String>

        fun render(viewState: ViewState)
    }

    sealed class ViewState {

        object LoadingState : ViewState()
        data class DataState(val stocks: List<Stock>) : ViewState()
        data class ErrorState(val error: Throwable) : ViewState()
    }
}