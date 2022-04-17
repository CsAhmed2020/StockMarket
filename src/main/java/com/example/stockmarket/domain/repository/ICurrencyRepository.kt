package com.example.stockmarket.domain.repository

import com.example.stockmarket.common.ResultWrapper
import com.example.stockmarket.domain.model.currency_converter.ConversionFactor
import com.example.stockmarket.domain.model.currency_converter.Currency

interface ICurrencyRepository {
    suspend fun getAllCurrencies(): ResultWrapper<Exception, List<Currency>>

    suspend fun getConversionFactor(
        baseCurrency: Currency,
        targetCurrency: Currency
    ): ResultWrapper<Exception, ConversionFactor>
}