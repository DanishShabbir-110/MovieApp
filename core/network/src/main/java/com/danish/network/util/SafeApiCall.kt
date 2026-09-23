package com.danish.network.util

import android.os.Build
import androidx.annotation.RequiresExtension
import com.danish.network.error.ApiError
import com.danish.network.result.ApiResult
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResult<T> {
    return try {
        val response = apiCall()
        ApiResult.Success(data = response)
    } catch (ex: SocketTimeoutException) {
        ApiResult.Error(
            error = ApiError.Timeout
        )
    } catch (exception: IOException) {

        ApiResult.Error(
            error = ApiError.NoInternet
        )

    } catch (exception: HttpException) {
        ApiResult.Error(
            error = ApiError.Http(
                code = exception.code(),
            )
        )

    } catch (exception: Exception) {

        ApiResult.Error(
            error = ApiError.Unknown(
                message = exception.message
            )
        )
    }
}