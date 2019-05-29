package com.dmitry_kuzmin.ratesapp.menu.presentation.contracts

import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

interface IStart {

    interface View : MvpView {

        fun loadLastStocksIntent(): Observable<Boolean>

        fun render(state: ViewState)
    }

    sealed class ViewState {

        class ErrorState(val error: Throwable) : ViewState()

        class DataState(val list: List<Stock>) : ViewState()
    }
}