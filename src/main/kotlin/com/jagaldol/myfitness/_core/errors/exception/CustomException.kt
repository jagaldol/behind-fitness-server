package com.jagaldol.myfitness._core.errors.exception

import com.jagaldol.myfitness._core.utils.ApiUtils

class CustomException(
    private val errorCode: ErrorCode,
    private val errorMessage: String? = null
) : RuntimeException(errorMessage ?: errorCode.message) {
    fun status() = errorCode.httpStatus.value()

    fun body(): ApiUtils.Response<ErrorCode> = ApiUtils.error(errorCode, errorMessage)

}