package com.dmitry_kuzmin.ratesapp.rates.data

import com.dmitry_kuzmin.ratesapp.core.domain.model.Money
import com.dmitry_kuzmin.ratesapp.menu.domain.model.Stock
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class IEXStockModel(
    val symbol: String,
    val companyName: String,
    val latestPrice: BigDecimal,
    @SerializedName("high")
    val highPrie: BigDecimal,
    @SerializedName("low")
    val lowPrice: BigDecimal

) {
    fun toStockModel(): Stock {
        return Stock(
            symbol,
            companyName,
            Money(latestPrice),
            Money(lowPrice),
            Money(highPrie)
        )
    }
}