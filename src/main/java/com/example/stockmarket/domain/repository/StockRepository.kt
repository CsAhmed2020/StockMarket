package com.example.stockmarket.domain.repository

import com.example.stockmarket.domain.model.CompanyInfo
import com.example.stockmarket.domain.model.CompanyListing
import com.example.stockmarket.domain.model.IntradayInfo
import com.example.stockmarket.common.stockUtil.Result
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListing(
        fetchRemoteData: Boolean,
        searchQuery: String
    ): Flow<Result<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Result<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Result<CompanyInfo>

}