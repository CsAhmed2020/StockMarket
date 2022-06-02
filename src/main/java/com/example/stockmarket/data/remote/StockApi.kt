package com.example.stockmarket.data.remote

import com.example.stockmarket.data.remote.dto.CompanyInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    //Summary of IBM
    //https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=IBM&apikey=KQNM89KA6DBLKPRO

    //Intraday of IBM
    //https://www.alphavantage.co/query?function=OVERVIEW&symbol=IBM&apikey=demo


    companion object {
        const val API_KEY = "KQNM89KA6DBLKPRO"
        const val BASE_URL = "https://alphavantage.co"
    }

    @GET("query?function=LISTING_STATUS")
    suspend fun getCompanyListing(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): CompanyInfoDto


}