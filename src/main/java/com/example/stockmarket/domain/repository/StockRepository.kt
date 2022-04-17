package com.example.stockmarket.domain.repository

import com.example.stockmarket.domain.model.CompanyInfo
import com.example.stockmarket.domain.model.CompanyListing
import com.example.stockmarket.domain.model.IntradayInfo
import com.example.stockmarket.common.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> /** we shouldn't use CompanyListingEntity here as domain layer shouldn't access the data layer  */

    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>

}