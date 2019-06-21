package com.dmitry_kuzmin.ratesapp.rates.presentation

import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockListTypes
import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockRepository
import com.dmitry_kuzmin.ratesapp.rates.presentation.contracts.IRatesList
import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RateListPresenter : MviBasePresenter<IRatesList.View, IRatesList.ViewState>() {

    private val repository = StockRepository()

    override fun bindIntents() {

        val observable = intent(IRatesList.View::loadIntents)
            .flatMap {
                repository.getStocks(listOf(StockListTypes.MOST_ACTIVE))
                    .map {
                        IRatesList.ViewState.DataState(it) as IRatesList.ViewState
                    }
                    .startWith(IRatesList.ViewState.LoadingState)
                    .onErrorReturn { IRatesList.ViewState.ErrorState(it) }
                    .subscribeOn(Schedulers.io())
            }.observeOn(AndroidSchedulers.mainThread())


        subscribeViewState(observable, IRatesList.View::render)
    }
}