package com.example.stockmarket.data.repository

import com.example.stockmarket.common.ResultWrapper
import com.example.stockmarket.data.remote.IRemoteDataSource
import com.example.stockmarket.data.remote.dto.toDomain
import com.example.stockmarket.domain.model.currency_converter.ConversionFactor
import com.example.stockmarket.domain.model.currency_converter.Currency
import com.example.stockmarket.domain.repository.ICurrencyRepository


class CurrencyRepositoryImpl (
    private val remoteDataSource: IRemoteDataSource
): ICurrencyRepository {
    override suspend fun getAllCurrencies(): ResultWrapper<Exception, List<Currency>> =
        when (val response = remoteDataSource.getAllCurrencies()) {
            is ResultWrapper.Failure -> response
            is ResultWrapper.Success -> ResultWrapper.build {
                response.result.toDomain()
            }
        }

    override suspend fun getConversionFactor(
        baseCurrency: Currency,
        targetCurrency: Currency
    ): ResultWrapper<Exception, ConversionFactor> =
        when (val response = remoteDataSource.getConversionFactor(baseCurrency, targetCurrency)) {
            is ResultWrapper.Failure -> response
            is ResultWrapper.Success -> ResultWrapper.build {
                response.result.toDomain(baseCurrency, targetCurrency)
            }
        }
}