package com.dmitry_kuzmin.ratesapp.rates.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmitry_kuzmin.ratesapp.R
import com.dmitry_kuzmin.ratesapp.rates.presentation.contracts.IRateFilter
import com.dmitry_kuzmin.ratesapp.rates.presentation.presenters.RateFilterPresenter
import com.hannesdorfmann.mosby3.mvi.MviFragment
import io.reactivex.Observable

class RateFilterFragment : MviFragment<IRateFilter.View, RateFilterPresenter>(), IRateFilter.View {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_rate_filter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun loadIntents(): Observable<String> {
        return Observable.just("Rates")
    }

    override fun render(viewState: IRateFilter.ViewState) {

    }

    override fun createPresenter(): RateFilterPresenter {
        return RateFilterPresenter()
    }
}