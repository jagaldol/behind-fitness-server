package com.jagaldol.behind.fitness.workout.session.controller

import com.jagaldol.behind.fitness._core.security.CustomUserDetails
import com.jagaldol.behind.fitness._core.utils.ApiUtils
import com.jagaldol.behind.fitness.workout.session.dto.SessionRequest
import com.jagaldol.behind.fitness.workout.session.service.SessionService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/sessions")
class SessionController(
    private val sessionService: SessionService
) {
    @PostMapping
    fun create(
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestBody @Valid requestDto: SessionRequest.CreateDto,
        errors: Errors
    ) = ResponseEntity.ok().body(ApiUtils.success(sessionService.create(userDetails.userId, requestDto)))

    @PutMapping("/{sessionId}")
    fun update(
        @PathVariable sessionId: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestBody @Valid requestDto: SessionRequest.UpdateDto,
        errors: Errors
    ): ResponseEntity<ApiUtils.Response<Any?>> {
        sessionService.update(sessionId, userDetails.userId, requestDto)
        return ResponseEntity.ok().body(ApiUtils.success())
    }

    @GetMapping
    fun get(
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestParam(value = "page", required = false, defaultValue = "1") page: Int?,
        @RequestParam(value = "date", required = false) date: LocalDate?
    ) = ResponseEntity.ok().body(ApiUtils.success(sessionService.get(userDetails.userId, page!!, date)))

    @GetMapping("/{sessionId}")
    fun getById(
        @PathVariable sessionId: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails,
    ) = ResponseEntity.ok().body(ApiUtils.success(sessionService.getById(userDetails.userId, sessionId)))

    @DeleteMapping("/{sessionId}")
    fun delete(
        @PathVariable sessionId: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails,
    ): ResponseEntity<ApiUtils.Response<Any?>> {
        sessionService.delete(sessionId, userDetails.userId)
        return ResponseEntity.ok().body(ApiUtils.success())
    }

    @GetMapping("/dates")
    fun getDates(
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestParam(value = "month", required = false) month: String?
    ) = ResponseEntity.ok().body(ApiUtils.success(sessionService.getDates(userDetails.userId, month)))
}