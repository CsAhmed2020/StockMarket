package com.example.stockmarket.common.stockUtil

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(val isLoading: Boolean = true): Result<T>(null)
    class Success<T>(data: T?): Result<T>(data)
    class Error<T>( data: T? = null,message: String): Result<T>(data, message)

}
