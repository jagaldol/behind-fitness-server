package com.jagaldol.behind.fitness.inbody.service

import com.jagaldol.behind.fitness.inbody.repository.InbodyRepository
import com.jagaldol.behind.fitness.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class InbodyService(
    private val userRepository: UserRepository,
    private val inbodyRepository: InbodyRepository
)