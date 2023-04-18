package com.example.lospibes.utils

import com.example.lospibes.core.model.ErrorResponse
import com.google.gson.Gson
import retrofit2.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseApiResponse {
    fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<NetworkResult<T>> = flow {
        try {
            emit(NetworkResult.Loading())

            val response = apiCall()

            if (response.isSuccessful) {
                val body = response.body()

                emit(
                    NetworkResult.Success(
                        data = body
                    )
                )
            } else {
                val errorResponse =
                    Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)

                emit(
                    NetworkResult.Error(
                        message = errorResponse
                    )
                )
            }
        } catch (e: Exception) {
            emit(
                NetworkResult.Error(
                    message = "Algo salió mal"
                )
            )
        }
    }
}