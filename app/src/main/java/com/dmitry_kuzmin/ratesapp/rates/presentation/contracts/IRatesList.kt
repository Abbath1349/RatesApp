package com.dmitry_kuzmin.ratesapp.rates.presentation.contracts

import com.hannesdorfmann.mosby3.mvp.MvpView

interface IRatesList {

    interface View : MvpView {

        fun loadStocks()
    }

    sealed class ViewState {

        object LoadingState : ViewState()
        object DataState : ViewState()
        object ErrorState : ViewState()
    }
}