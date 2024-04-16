package com.jagaldol.behind.fitness.workout.session.service

import com.jagaldol.behind.fitness._core.errors.exception.CustomException
import com.jagaldol.behind.fitness._core.errors.exception.ErrorCode
import com.jagaldol.behind.fitness._core.utils.CreateResponseDto
import com.jagaldol.behind.fitness.user.repository.UserRepository
import com.jagaldol.behind.fitness.workout.session.Session
import com.jagaldol.behind.fitness.workout.session.dto.SessionRequest
import com.jagaldol.behind.fitness.workout.session.repository.SessionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class SessionService(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository
) {
    @Transactional
    fun create(userId: Long, requestDto: SessionRequest.CreateDto): CreateResponseDto {
        val user = userRepository.findByIdOrNull(userId) ?: throw CustomException(ErrorCode.NOT_FOUND_USER)
        val workoutSession = Session(user, requestDto.date!!, requestDto.startTime, requestDto.endTime)

        return CreateResponseDto(sessionRepository.save(workoutSession).id ?: throw CustomException(ErrorCode.SERVER_ERROR))
    }
}