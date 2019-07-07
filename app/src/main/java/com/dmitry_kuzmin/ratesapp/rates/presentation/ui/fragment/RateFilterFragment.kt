package com.dmitry_kuzmin.ratesapp.rates.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.dmitry_kuzmin.ratesapp.R
import com.dmitry_kuzmin.ratesapp.core.presentation.ui.GridMarginItemDecoration
import com.dmitry_kuzmin.ratesapp.rates.presentation.contracts.IRateFilter
import com.dmitry_kuzmin.ratesapp.rates.presentation.model.RateFilterPM
import com.dmitry_kuzmin.ratesapp.rates.presentation.presenters.RateFilterPresenter
import com.dmitry_kuzmin.ratesapp.rates.presentation.ui.adapters.StockTypeAdapter
import com.hannesdorfmann.mosby3.mvi.MviFragment
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_rate_filter.*

class RateFilterFragment : MviFragment<IRateFilter.View, RateFilterPresenter>(), IRateFilter.View {

    private lateinit var adapter: StockTypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = StockTypeAdapter { }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_rate_filter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stockTypeRecyclerView.layoutManager = GridLayoutManager(context, 3)
        stockTypeRecyclerView.adapter = adapter

        val decoration = GridMarginItemDecoration(resources.getDimension(R.dimen.rate_list_item_margin).toInt())
        stockTypeRecyclerView.addItemDecoration(decoration)
    }

    override fun loadIntents(): Observable<String> {
        return Observable.just("Rates")
    }

    override fun render(viewState: IRateFilter.ViewState) {
        when (viewState) {
            is IRateFilter.ViewState.DataState -> renderContent(viewState.rateFilter)
        }
    }

    private fun renderContent(rateFilter: RateFilterPM) {
        adapter.updateItems(rateFilter.stockTypeItems)
    }

    override fun createPresenter(): RateFilterPresenter {
        return RateFilterPresenter()
    }
}