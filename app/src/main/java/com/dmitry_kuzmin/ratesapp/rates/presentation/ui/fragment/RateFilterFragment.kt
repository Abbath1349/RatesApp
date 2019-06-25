package com.dmitry_kuzmin.ratesapp.rates.presentation.ui.fragment

import com.dmitry_kuzmin.ratesapp.rates.presentation.contracts.IRateFilter
import com.dmitry_kuzmin.ratesapp.rates.presentation.presenters.RateFilterPresenter
import com.hannesdorfmann.mosby3.mvi.MviFragment
import io.reactivex.Observable

class RateFilterFragment : MviFragment<IRateFilter.View, RateFilterPresenter>(), IRateFilter.View{
    override fun loadIntents(): Observable<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun render(viewState: IRateFilter.ViewState) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPresenter(): RateFilterPresenter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}