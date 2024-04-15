package com.jagaldol.behind.fitness.sport.controller

import com.jagaldol.behind.fitness._core.security.CustomUserDetails
import com.jagaldol.behind.fitness._core.utils.ApiUtils
import com.jagaldol.behind.fitness.sport.dto.SportRequest
import com.jagaldol.behind.fitness.sport.service.SportService
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

    @PutMapping("/{sportId}")
    fun update(
        @PathVariable sportId: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestBody @Valid requestDto: SportRequest.UpdateDto,
        errors: Errors,
    ): ResponseEntity<ApiUtils.Response<Any?>> {
        sportService.update(sportId, userDetails.userId, requestDto)
        return ResponseEntity.ok().body(ApiUtils.success())
    }

    @DeleteMapping("/{sportId}")
    fun delete(
        @PathVariable sportId: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails
    ): ResponseEntity<ApiUtils.Response<Any?>> {
        sportService.delete(sportId, userDetails.userId)
        return ResponseEntity.ok().body(ApiUtils.success())
    }
}