package com.jagaldol.behind.fitness._core.errors

import com.jagaldol.behind.fitness._core.errors.exception.CustomException
import com.jagaldol.behind.fitness._core.errors.exception.ErrorCode
import com.jagaldol.behind.fitness._core.utils.ApiUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun customError(e: CustomException): ResponseEntity<Any> = ResponseEntity.status(e.status()).body(e.body())

    @ExceptionHandler(Exception::class)
    fun unknownServerError(e: Exception) =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiUtils.error(ErrorCode.UNKNOWN_SERVER_ERROR, e.message))
            .also { e.printStackTrace() }
}