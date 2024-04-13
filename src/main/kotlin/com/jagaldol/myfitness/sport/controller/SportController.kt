package com.jagaldol.myfitness.sport.controller

import com.jagaldol.myfitness._core.security.CustomUserDetails
import com.jagaldol.myfitness._core.utils.ApiUtils
import com.jagaldol.myfitness.sport.dto.SportRequest
import com.jagaldol.myfitness.sport.service.SportService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sports")
class SportController(
    private val sportService: SportService
) {
    @PostMapping
    fun create(
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestBody @Valid requestDto: SportRequest.CreateDto,
        errors: Errors
    ) = ResponseEntity.ok().body(ApiUtils.success(sportService.create(userDetails.userId, requestDto)))

    @GetMapping
    fun get(
        @AuthenticationPrincipal userDetails: CustomUserDetails
    ) = ResponseEntity.ok().body(ApiUtils.success(sportService.get(userDetails.userId)))
}