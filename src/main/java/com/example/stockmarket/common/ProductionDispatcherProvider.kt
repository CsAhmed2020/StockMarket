package com.example.stockmarket.common

import com.example.stockmarket.common.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object ProductionDispatcherProvider: DispatcherProvider {
    override fun UI(): CoroutineContext {
        return Dispatchers.Main
    }

    override fun IO(): CoroutineContext {
        return Dispatchers.IO
    }
}