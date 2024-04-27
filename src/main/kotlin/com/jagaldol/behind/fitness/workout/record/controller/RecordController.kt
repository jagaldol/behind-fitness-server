package com.jagaldol.behind.fitness.workout.record.controller

import com.jagaldol.behind.fitness._core.security.CustomUserDetails
import com.jagaldol.behind.fitness._core.utils.ApiUtils
import com.jagaldol.behind.fitness.workout.record.dto.RecordRequest
import com.jagaldol.behind.fitness.workout.record.service.RecordService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sessions")
class RecordController(
    private val recordService: RecordService
) {
    @PostMapping("/{sessionId}/records")
    fun create(
        @PathVariable sessionId: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestBody @Valid requestDto: RecordRequest.CreateDto,
        errors: Errors,
    ) = ResponseEntity.ok().body(ApiUtils.success(recordService.create(userDetails.userId, sessionId, requestDto)))

    @PutMapping("/records/{recordId}")
    fun update(
        @PathVariable recordId: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestBody @Valid requestDto: RecordRequest.UpdateDto,
        errors: Errors,
    ): ResponseEntity<ApiUtils.Response<Any?>> {
        recordService.update(userDetails.userId, recordId, requestDto)
        return ResponseEntity.ok().body(ApiUtils.success())
    }

    @DeleteMapping("/records/{recordId}")
    fun delete(
        @PathVariable recordId: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails,
    ): ResponseEntity<ApiUtils.Response<Any?>> {
        recordService.delete(userDetails.userId, recordId)
        return ResponseEntity.ok().body(ApiUtils.success())
    }
}