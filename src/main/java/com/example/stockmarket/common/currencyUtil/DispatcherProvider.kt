package com.example.stockmarket.common.currencyUtil

import kotlin.coroutines.CoroutineContext
//for currency converter
interface DispatcherProvider {
    fun UI(): CoroutineContext
    fun IO(): CoroutineContext
}