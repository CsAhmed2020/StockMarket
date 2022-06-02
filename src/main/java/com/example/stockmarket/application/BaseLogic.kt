package com.example.stockmarket.application

import kotlinx.coroutines.Job

//for currency converter
abstract class BaseLogic<EVENT> {
    protected lateinit var jobTracker: Job

    abstract fun onEvent(event: EVENT)
}