package com.dmitry_kuzmin.ratesapp.rates.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.dmitry_kuzmin.ratesapp.RatesApplication
import com.dmitry_kuzmin.ratesapp.rates.domain.model.RateFilter
import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockListTypes
import com.google.gson.Gson

private const val PREFS_NAME = "rate_prefs"
private const val FILTER = "rateFilter"

class RatePrefs {

    private val prefs: SharedPreferences by lazy {
        RatesApplication.context!!.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getFilter(): RateFilter {
        val json = prefs.getString(PREFS_NAME, null)

        return json?.let { Gson().fromJson(json, RateFilter::class.java) }
            ?: RateFilter(listOf(StockListTypes.ALL))
    }

    fun setFilter(rateFilter: RateFilter, async: Boolean = true) {
        val editor = prefs.edit().putString(FILTER, Gson().toJson(rateFilter))
        if (async) editor.apply() else editor.commit()
    }
}