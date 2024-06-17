package com.jagaldol.behind.fitness.inbody.controller

import com.jagaldol.behind.fitness._core.security.CustomUserDetails
import com.jagaldol.behind.fitness._core.utils.ApiUtils
import com.jagaldol.behind.fitness.inbody.dto.InbodyRequest
import com.jagaldol.behind.fitness.inbody.service.InbodyService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/inbody")
class InbodyController(
    private val inbodyService: InbodyService
) {
    @GetMapping
    fun get(
        @AuthenticationPrincipal userDetails: CustomUserDetails
    ) = ResponseEntity.ok().body(ApiUtils.success(inbodyService.get(userDetails.userId)))

    @PostMapping
    fun create(
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestBody @Valid requestDto: InbodyRequest.CreateDto,
        errors: Errors
    ) = ResponseEntity.ok().body(ApiUtils.success(inbodyService.create(userDetails.userId, requestDto)))
}