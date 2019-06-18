package com.dmitry_kuzmin.ratesapp.settings.data.prefs

interface ITokenProvider {

    fun getToken(): String
}