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
            .flatMapSingle { repository.getStocks(listOf(StockListTypes.MOST_ACTIVE)) }
            .map { IRatesList.ViewState.DataState(it) }
            .cast(IRatesList.ViewState::class.java)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .startWith { IRatesList.ViewState.LoadingState }
            .onErrorReturn { IRatesList.ViewState.ErrorState }

        subscribeViewState(observable, IRatesList.View::render)
    }
}