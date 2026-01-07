package com.jvmartinez.r_mortyhub.core.api

import android.util.Log
import retrofit2.Response

suspend fun <T> apiResponse(apiResponse: suspend () -> Response<T>): DataResult<T> {
    return try {
        val response = apiResponse()
        Log.i("apiResponse", response.toString())

        when {
            response.code() == 500 -> DataResult.Exception(
                Exception("Internal server error")
            )

            response.isSuccessful -> response.body()?.let {
                DataResult.Success(it)
            }
                ?: DataResult.Exception(
                    Exception("Response body is null")
                )

            else -> {
                DataResult.Exception(
                    Exception("Response body is null")
                )
            }
        }
    } catch (e: Exception) {
        DataResult.Exception(e)
    }
}