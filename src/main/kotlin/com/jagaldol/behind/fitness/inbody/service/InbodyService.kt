package com.jagaldol.behind.fitness.inbody.service

import com.jagaldol.behind.fitness.inbody.dto.InbodyResponse
import com.jagaldol.behind.fitness.inbody.repository.InbodyRepository
import com.jagaldol.behind.fitness.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class InbodyService(
    private val userRepository: UserRepository,
    private val inbodyRepository: InbodyRepository
) {
    fun get(userId: Long) = inbodyRepository.findByUserIdOrderByDate(userId).map { InbodyResponse.InbodyDto(it) }
}