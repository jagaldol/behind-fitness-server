package com.jagaldol.myfitness.user.controller

import com.jagaldol.myfitness._core.security.CustomUserDetails
import com.jagaldol.myfitness._core.security.JwtProvider
import com.jagaldol.myfitness._core.utils.ApiUtils
import com.jagaldol.myfitness.user.dto.UserRequest
import com.jagaldol.myfitness.user.service.UserService
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

private val log = KotlinLogging.logger {}

@RestController
class UserController(
    private val userService: UserService,
    private val jwtProvider: JwtProvider
) {


    @PostMapping("/login")
    fun login(@RequestBody @Valid requestDto: UserRequest.LoginDto, errors: Errors): ResponseEntity<ApiUtils.Response<Any?>> {
        val (access, refresh) = userService.login(requestDto)

        return ResponseEntity.ok().header(jwtProvider.header, access)
            .header(HttpHeaders.SET_COOKIE, createRefreshTokenCookie(refresh, jwtProvider.refreshExp).toString())
            .body(ApiUtils.success())

    }

    @PostMapping("/authentication")
    fun reIssueTokens(@CookieValue("refreshToken") refreshToken: String): ResponseEntity<ApiUtils.Response<Any?>> {
        val (access, refresh) = userService.reIssueTokens(refreshToken)

        return ResponseEntity.ok().header(jwtProvider.header, access)
            .header(HttpHeaders.SET_COOKIE, createRefreshTokenCookie(refresh, jwtProvider.refreshExp).toString())
            .body(ApiUtils.success())
    }

    @PostMapping("/logout")
    fun logout(@AuthenticationPrincipal userDetails: CustomUserDetails): ResponseEntity<ApiUtils.Response<Any?>> {
        userService.logout(userDetails.userId)
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, createRefreshTokenCookie("", 0).toString())
            .body(ApiUtils.success())
    }


    private fun createRefreshTokenCookie(refreshToken: String, exp: Long) =
        ResponseCookie.from("refreshToken", refreshToken)
            .httpOnly(true) // javascript 접근 방지
            .secure(true) // https 통신 강제
            .sameSite("None")
            .maxAge(exp)
            .build()
}

