package com.jagaldol.behind.fitness.workout.session.controller

import com.jagaldol.behind.fitness.workout.session.service.SessionService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sessions")
class SessionController(
    private val sessionService: SessionService
)