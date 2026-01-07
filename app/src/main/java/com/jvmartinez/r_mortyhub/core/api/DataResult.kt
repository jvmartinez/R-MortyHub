package com.jvmartinez.r_mortyhub.core.api

sealed class DataResult<out T> {
    data class Success<out T>(val data: T) : DataResult<T>()
    data class Exception(val throwable: Throwable) : DataResult<Nothing>()
}
