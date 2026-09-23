package com.danish.network.result

import com.danish.network.error.ApiError

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val error: ApiError, val code: Int? = null) : ApiResult<Nothing>()
}