package com.jagaldol.behind.fitness._core.errors.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val httpStatus: HttpStatus,
    val message: String
) {
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "토큰 검증 실패"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않았습니다."),
    PERMISSION_DENIED(HttpStatus.FORBIDDEN, "권한이 없습니다."),

    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    NOT_FOUND_DATA(HttpStatus.NOT_FOUND, "존재하지 않는 데이터에 대한 접근입니다"),

    LOGIN_FAILED(HttpStatus.BAD_REQUEST, "로그인에 실패하였습니다."),
    INVALID_REQUEST_DATA(HttpStatus.BAD_REQUEST, "올바른 양식이 아닙니다."),
    DUPLICATED_DATA(HttpStatus.CONFLICT, "이미 존재하는 데이터입니다."),
    REFERENCED_DATA_EXISTS(HttpStatus.CONFLICT, "사용되고 있는 데이터입니다."),

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류입니다. 다시 시도해주세요."),
    UNKNOWN_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알수 없는 서버 내부 에러입니다.")
}