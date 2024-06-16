package com.jagaldol.behind.fitness.inbody.controller

import com.jagaldol.behind.fitness._core.security.CustomUserDetails
import com.jagaldol.behind.fitness._core.utils.ApiUtils
import com.jagaldol.behind.fitness.inbody.service.InbodyService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/inbody")
class InbodyController(
    private val inbodyService: InbodyService
) {
    @GetMapping
    fun get(
        @AuthenticationPrincipal userDetails: CustomUserDetails
    ) = ResponseEntity.ok().body(ApiUtils.success(inbodyService.get(userDetails.userId)))
}