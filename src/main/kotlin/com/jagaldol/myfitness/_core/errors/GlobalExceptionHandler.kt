package com.jagaldol.myfitness._core.errors

import com.jagaldol.myfitness._core.errors.exception.CustomException
import com.jagaldol.myfitness._core.errors.exception.ErrorCode
import com.jagaldol.myfitness._core.utils.ApiUtils
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

private val log = KotlinLogging.logger {}

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun customError(e: CustomException): ResponseEntity<Any> = ResponseEntity.status(e.status()).body(e.body())

    @ExceptionHandler(Exception::class)
    fun unknownServerError(e: Exception) =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiUtils.error(ErrorCode.UNKNOWN_SERVER_ERROR, e.message))
            .also { e.printStackTrace() }
}