package com.dmitry_kuzmin.ratesapp

import android.app.Application
import android.content.Context

class RatesApplication : Application() {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}