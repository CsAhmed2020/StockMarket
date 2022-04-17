package com.example.stockmarket.common

import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {
    fun UI(): CoroutineContext
    fun IO(): CoroutineContext
}