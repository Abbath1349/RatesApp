package com.dmitry_kuzmin.ratesapp.rates.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dmitry_kuzmin.ratesapp.R
import com.dmitry_kuzmin.ratesapp.core.presentation.ui.GridMarginItemDecoration
import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockListTypes
import com.dmitry_kuzmin.ratesapp.rates.presentation.contracts.IRateFilter
import com.dmitry_kuzmin.ratesapp.rates.presentation.model.RateFilterPM
import com.dmitry_kuzmin.ratesapp.rates.presentation.presenters.RateFilterPresenter
import com.dmitry_kuzmin.ratesapp.rates.presentation.ui.adapters.StockTypeAdapter
import com.hannesdorfmann.mosby3.mvi.MviFragment
import com.jakewharton.rxbinding2.view.RxView
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
        return Observable.just("Begin")
    }

    override fun render(viewState: IRateFilter.ViewState) {
        when (viewState) {
            is IRateFilter.ViewState.DataState -> renderContent(viewState.rateFilter)
            is IRateFilter.ViewState.ExitState -> findNavController()
                .navigate(
                    R.id.action_rateFilterFragment_to_rateListFragment,
                    null,
                    NavOptions.Builder().setLaunchSingleTop(true).build()
                )
        }
    }

    override fun onSaveClick(): Observable<List<StockListTypes>> {
        return RxView.clicks(saveFilterButton).map { listOf(StockListTypes.GAINERS) }
    }

    private fun renderContent(rateFilter: RateFilterPM) {
        adapter.updateItems(rateFilter.stockTypeItems)
    }

    override fun createPresenter(): RateFilterPresenter {
        return RateFilterPresenter()
    }
}