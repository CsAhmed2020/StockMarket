package com.example.stockmarket.data.remote

import com.example.stockmarket.common.DispatcherProvider
import com.example.stockmarket.common.ResultWrapper
import com.example.stockmarket.data.remote.IRemoteDataSource
import com.example.stockmarket.data.remote.dto.PairConversion
import com.example.stockmarket.data.remote.dto.SupportedCodes
import com.example.stockmarket.domain.model.currency_converter.Currency
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class RemoteDataSourceImpl(
    private val baseUrl: String,
    private val dispatcherProvider: DispatcherProvider
): IRemoteDataSource {
    private val client = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        engine {
            connectTimeout = 60_000
            socketTimeout = 60_000
        }
    }

    override suspend fun getAllCurrencies(): ResultWrapper<Exception, SupportedCodes> =
        ResultWrapper.build {
            client.get {
                url("$baseUrl/codes")
            }
        }

    override suspend fun getConversionFactor(
        baseCurrency: Currency,
        targetCurrency: Currency
    ): ResultWrapper<Exception, PairConversion> =
        ResultWrapper.build {
            client.get {
                url("$baseUrl/pair/${baseCurrency.code}/${targetCurrency.code}")
            }
        }
}