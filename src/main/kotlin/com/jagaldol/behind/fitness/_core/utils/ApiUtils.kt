package com.jagaldol.behind.fitness._core.utils

import com.jagaldol.behind.fitness._core.errors.exception.ErrorCode
import org.springframework.http.HttpStatus

class ApiUtils {
    companion object {
        fun <T> success(response: T? = null, status: Int = HttpStatus.OK.value()) =
            Response(status, response, null)

        fun error(errorCode: ErrorCode, errorMessage: String?): Response<ErrorCode> =
            Response(errorCode.httpStatus.value(), errorCode, errorMessage ?: errorCode.message)
    }

    data class Response<T>(
        val status: Int,
        val response: T?,
        val errorMessage: String?
    )
}