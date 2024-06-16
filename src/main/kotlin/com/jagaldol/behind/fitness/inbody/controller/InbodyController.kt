package com.jagaldol.behind.fitness.inbody.controller

import com.jagaldol.behind.fitness.inbody.service.InbodyService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/inbody")
class InbodyController(
    private val inbodyService: InbodyService
)