package com.jvmartinez.r_mortyhub.ui.base

sealed class StatusData<out T> {
    object Loading : StatusData<Nothing>()
    data class Success<out T>(val data: T) : StatusData<T>()
    data class Error(val error: String, val exception: Throwable? = null) : StatusData<Nothing>()
}
