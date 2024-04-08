package com.jagaldol.myfitness._core.errors.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    val message: String
) {
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "토큰 검증 실패"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않았습니다."),
    PERMISSION_DENIED(HttpStatus.FORBIDDEN, "권한이 없습니다."),

    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),


    LOGIN_FAILED(HttpStatus.BAD_REQUEST, "로그인에 실패하였습니다."),
    INVALID_REQUEST_DATA(HttpStatus.BAD_REQUEST, "올바른 양식이 아닙니다."),

    UNKNOWN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알수 없는 서버 내부 에러입니다.")
}