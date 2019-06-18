package com.dmitry_kuzmin.ratesapp.rates.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmitry_kuzmin.ratesapp.R
import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import com.dmitry_kuzmin.ratesapp.rates.presentation.RateListPresenter
import com.dmitry_kuzmin.ratesapp.rates.presentation.contracts.IRatesList
import com.hannesdorfmann.mosby3.mvi.MviFragment
import io.reactivex.Observable

class RateListFragment : MviFragment<IRatesList.View, RateListPresenter>(), IRatesList.View {

    override fun createPresenter(): RateListPresenter {
        return RateListPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_rates_list, container, false)


    override fun loadIntents(): Observable<List<Stock>> {
        return Observable.just(listOf())
    }

    override fun render(viewState: IRatesList.ViewState) {
        when (viewState) {
            is IRatesList.ViewState.LoadingState -> renderLoading()
            is IRatesList.ViewState.DataState -> renderContent(viewState.stocks)
            is IRatesList.ViewState.ErrorState -> renderError()
        }
    }

    private fun renderLoading() {

    }

    private fun renderContent(stocks: List<Stock>) {
        val size = stocks.size
    }

    private fun renderError() {

    }

}