package com.dmitry_kuzmin.ratesapp.rates.presentation.contracts

import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface IRatesList {

    interface View : MvpView {

        fun loadIntents(): Observable<List<Stock>>

        fun render(viewState: ViewState)
    }

    sealed class ViewState {

        object LoadingState : ViewState()
        class DataState(val stocks: List<Stock>) : ViewState()
        object ErrorState : ViewState()
    }
}