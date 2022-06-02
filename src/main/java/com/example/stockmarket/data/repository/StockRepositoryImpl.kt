package com.example.stockmarket.data.repository

import com.example.stockmarket.data.csv.CsvParser
import com.example.stockmarket.data.local.StockDatabase
import com.example.stockmarket.data.mapper.toCompanyInfo
import com.example.stockmarket.data.mapper.toCompanyListing
import com.example.stockmarket.data.mapper.toCompanyListingEntity
import com.example.stockmarket.data.remote.StockApi
import com.example.stockmarket.domain.model.CompanyInfo
import com.example.stockmarket.domain.model.CompanyListing
import com.example.stockmarket.domain.model.IntradayInfo
import com.example.stockmarket.domain.repository.StockRepository
import com.example.stockmarket.common.stockUtil.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val companyListingsParser: CsvParser<CompanyListing>,
    private val intradayInfoParser: CsvParser<IntradayInfo>
): StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListing(
        fetchRemoteData: Boolean,
        searchQuery: String
    ): Flow<Result<List<CompanyListing>>> {
        return flow {
            emit(Result.Loading(true))
            val localListings = dao.searchCompanyListing(searchQuery)
            emit(
                Result.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && searchQuery.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchRemoteData
            if(shouldJustLoadFromCache) {
                emit(Result.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getCompanyListing()
                companyListingsParser.parse(response.byteStream())
            } catch(e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListing(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(
                    Result.Success(
                    data = dao
                        .searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Result.Loading(false))

            }
        }
    }

    override suspend fun getIntradayInfo(symbol: String): Result<List<IntradayInfo>> {
        return try {
            val response = api.getIntradayInfo(symbol)
            val results = intradayInfoParser.parse(response.byteStream())
            Result.Success(results)
        } catch(e: IOException) {
            e.printStackTrace()
            Result.Error(
                message = "Couldn't load intraday info"
            )
        } catch(e: HttpException) {
            e.printStackTrace()
            Result.Error(
                message = "Couldn't load intraday info"
            )
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Result<CompanyInfo> {
        return try {
            val result = api.getCompanyInfo(symbol)
            Result.Success(result.toCompanyInfo())
        } catch(e: IOException) {
            e.printStackTrace()
            Result.Error(
                message = "Couldn't load company info<IOEx>"
            )
        } catch(e: HttpException) {
            e.printStackTrace()
            Result.Error(
                message = "Couldn't load company info<HttpEx>"
            )
        }
    }
}