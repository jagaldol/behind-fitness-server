package com.jagaldol.behind.fitness.workout.record.service

import com.jagaldol.behind.fitness._core.errors.exception.CustomException
import com.jagaldol.behind.fitness._core.errors.exception.ErrorCode
import com.jagaldol.behind.fitness._core.utils.CreateResponseDto
import com.jagaldol.behind.fitness.sport.repository.SportRepository
import com.jagaldol.behind.fitness.user.repository.UserRepository
import com.jagaldol.behind.fitness.workout.record.Record
import com.jagaldol.behind.fitness.workout.record.dto.RecordRequest
import com.jagaldol.behind.fitness.workout.record.repository.RecordRepository
import com.jagaldol.behind.fitness.workout.session.repository.SessionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class RecordService(
    private val recordRepository: RecordRepository,
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
    private val sportRepository: SportRepository
) {
    @Transactional
    fun create(userId: Long, sessionId: Long, requestDto: RecordRequest.CreateDto): CreateResponseDto {
        val workoutSession = sessionRepository.findByIdOrNull(sessionId) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
        val sport = sportRepository.findByIdOrNull(requestDto.sportId) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
        if (workoutSession.user.id != userId || sport.user.id != userId) throw CustomException(ErrorCode.PERMISSION_DENIED)

        val newRecord = Record(workoutSession, sport)

        return CreateResponseDto(recordRepository.save(newRecord).id ?: throw CustomException(ErrorCode.SERVER_ERROR))
    }

    @Transactional
    fun update(userId: Long, recordId: Long, requestDto: RecordRequest.UpdateDto) {
        requestDto.sportId?.let {
            val workoutRecord = recordRepository.findByIdOrNullFetchSession(recordId) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
            val sport = sportRepository.findByIdOrNull(it) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
            if (workoutRecord.session.user.id != userId || sport.user.id != userId) throw CustomException(ErrorCode.PERMISSION_DENIED)

            workoutRecord.sport = sport
        }
    }
}