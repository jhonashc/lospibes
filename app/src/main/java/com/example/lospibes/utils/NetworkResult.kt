package com.example.lospibes.utils

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: Any? = null
) {
    class Success<T>(data: T?) : NetworkResult<T>(data)
    class Error<T>(message: Any, data: T? = null) : NetworkResult<T>(data, message)
    class Loading<T> : NetworkResult<T>()
}
