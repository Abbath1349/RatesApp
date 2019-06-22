package com.dmitry_kuzmin.ratesapp.rates.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitry_kuzmin.ratesapp.R
import com.dmitry_kuzmin.ratesapp.core.presentation.ui.MarginItemDecoration
import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import com.dmitry_kuzmin.ratesapp.rates.presentation.RateListAdapter
import com.dmitry_kuzmin.ratesapp.rates.presentation.RateListPresenter
import com.dmitry_kuzmin.ratesapp.rates.presentation.contracts.IRatesList
import com.hannesdorfmann.mosby3.mvi.MviFragment
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_rates_list.*

class RateListFragment : MviFragment<IRatesList.View, RateListPresenter>(), IRatesList.View {

    private lateinit var adapter: RateListAdapter

    override fun createPresenter(): RateListPresenter {
        return RateListPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = RateListAdapter { }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_rates_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ratesRecyclerView.layoutManager = LinearLayoutManager(context)
        val decoration = MarginItemDecoration(resources.getDimension(R.dimen.rate_list_item_margin).toInt())
        ratesRecyclerView.adapter = adapter
        ratesRecyclerView.addItemDecoration(decoration)
    }

    override fun loadIntents(): Observable<String> {
        return Observable.just("Hello")
    }

    override fun render(viewState: IRatesList.ViewState) {
        when (viewState) {
            is IRatesList.ViewState.LoadingState -> renderLoading()
            is IRatesList.ViewState.DataState -> renderContent(viewState.stocks)
            is IRatesList.ViewState.ErrorState -> renderError()
        }
    }

    private fun renderLoading() {
        contentGroup.visibility = View.GONE
        loadingView.visibility = View.VISIBLE
    }

    private fun renderContent(stocks: List<Stock>) {
        contentGroup.visibility = View.VISIBLE
        loadingView.visibility = View.GONE
        adapter.updateItems(stocks)
    }

    private fun renderError() {
        contentGroup.visibility = View.GONE
        loadingView.visibility = View.GONE
    }
}