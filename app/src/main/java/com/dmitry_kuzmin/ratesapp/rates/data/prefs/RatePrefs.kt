package com.dmitry_kuzmin.ratesapp.rates.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.dmitry_kuzmin.ratesapp.RatesApplication
import com.dmitry_kuzmin.ratesapp.rates.domain.model.Filter
import com.dmitry_kuzmin.ratesapp.rates.domain.repo.StockListTypes
import com.google.gson.Gson

private const val PREFS_NAME = "rate_prefs"
private const val FILTER = "filter"

class RatePrefs {

    private val prefs: SharedPreferences by lazy {
        RatesApplication.context!!.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getFilter(): Filter {
        val json = prefs.getString(PREFS_NAME, null)

        return json?.let { Gson().fromJson(json, Filter::class.java) }
            ?: Filter(listOf(StockListTypes.GAINERS))
    }

    fun setFilter(filter: Filter, async: Boolean = true) {
        val editor = prefs.edit().putString(FILTER, Gson().toJson(filter))
        if (async) editor.apply() else editor.commit()
    }
}