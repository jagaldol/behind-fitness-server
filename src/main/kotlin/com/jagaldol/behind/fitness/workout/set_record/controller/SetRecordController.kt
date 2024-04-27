package com.jagaldol.behind.fitness.workout.set_record.controller

import com.jagaldol.behind.fitness._core.security.CustomUserDetails
import com.jagaldol.behind.fitness._core.utils.ApiUtils
import com.jagaldol.behind.fitness.workout.set_record.dto.SetRecordRequest
import com.jagaldol.behind.fitness.workout.set_record.service.SetRecordService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/sessions/records")
class SetRecordController(
    private val setRecordService: SetRecordService
) {
    @PostMapping("/{recordId}/sets")
    fun create(
        @PathVariable recordId: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestBody @Valid requestDto: SetRecordRequest.CreateDto,
        errors: Errors
    ) = ResponseEntity.ok().body(ApiUtils.success(setRecordService.create(userDetails.userId, recordId, requestDto)))

    @PutMapping("/sets/{setId}")
    fun update(
        @PathVariable setId: Long,
        @AuthenticationPrincipal userDetails: CustomUserDetails,
        @RequestBody @Valid requestDto: SetRecordRequest.UpdateDto,
        errors: Errors,
    ): ResponseEntity<ApiUtils.Response<Any?>> {
        setRecordService.update(userDetails.userId, setId, requestDto)
        return ResponseEntity.ok().body(ApiUtils.success())
    }
}