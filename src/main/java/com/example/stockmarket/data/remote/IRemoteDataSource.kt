package com.example.stockmarket.data.remote

import com.example.stockmarket.common.currencyUtil.ResultWrapper
import com.example.stockmarket.data.remote.dto.PairConversion
import com.example.stockmarket.data.remote.dto.SupportedCodes
import com.example.stockmarket.domain.model.currency_converter.Currency

interface IRemoteDataSource {
    suspend fun getAllCurrencies(): ResultWrapper<Exception, SupportedCodes>

    suspend fun getConversionFactor(
        baseCurrency: Currency,
        targetCurrency: Currency
    ): ResultWrapper<Exception, PairConversion>
}