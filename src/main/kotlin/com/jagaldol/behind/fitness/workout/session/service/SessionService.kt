package com.jagaldol.behind.fitness.workout.session.service

import com.jagaldol.behind.fitness.workout.session.repository.SessionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class SessionService(
    private val sessionRepository: SessionRepository
)