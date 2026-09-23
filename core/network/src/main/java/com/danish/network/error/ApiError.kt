package com.danish.network.error

sealed class ApiError {
    data object NoInternet: ApiError()
    data object Timeout: ApiError()
    data class Http(val code: Int): ApiError()
    data class Unknown(val message: String?=null): ApiError()
}