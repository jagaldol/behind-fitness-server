package com.jagaldol.behind.fitness.workout.session.controller

import com.jagaldol.behind.fitness._core.security.CustomUserDetails
import com.jagaldol.behind.fitness._core.utils.ApiUtils
import com.jagaldol.behind.fitness.workout.session.dto.SessionRequest
import com.jagaldol.behind.fitness.workout.session.service.SessionService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}