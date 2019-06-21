package com.dmitry_kuzmin.ratesapp.core.domain.model

import java.math.BigDecimal

data class Money(val price: BigDecimal){

    override fun toString(): String {
        return price.toString()
    }
}