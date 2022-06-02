package com.example.stockmarket.di

import android.app.Application
import androidx.room.Room
import com.example.stockmarket.common.currencyUtil.ProductionDispatcherProvider
import com.example.stockmarket.data.local.StockDatabase
import com.example.stockmarket.data.remote.RemoteDataSourceImpl
import com.example.stockmarket.data.remote.StockApi
import com.example.stockmarket.data.repository.CurrencyRepositoryImpl
import com.example.stockmarket.presentation.currency_converter.CurrencyConverterViewModelImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): StockDatabase {
        return Room.databaseBuilder(
            app,
            StockDatabase::class.java,
            "stockdb.db"
        ).build()
    }

    val viewModel = CurrencyConverterViewModelImpl(
        CurrencyRepositoryImpl(
            RemoteDataSourceImpl(
                baseUrl = "https://v6.exchangerate-api.com/v6/921193234546802d48c02d67",
                dispatcherProvider = ProductionDispatcherProvider
            )
        ),
        ProductionDispatcherProvider
    )

}