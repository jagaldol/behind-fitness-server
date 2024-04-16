package com.jagaldol.behind.fitness.workout.session.service

import com.jagaldol.behind.fitness._core.errors.exception.CustomException
import com.jagaldol.behind.fitness._core.errors.exception.ErrorCode
import com.jagaldol.behind.fitness._core.utils.CreateResponseDto
import com.jagaldol.behind.fitness.user.repository.UserRepository
import com.jagaldol.behind.fitness.workout.record.dto.RecordDto
import com.jagaldol.behind.fitness.workout.record.repository.RecordRepository
import com.jagaldol.behind.fitness.workout.session.Session
import com.jagaldol.behind.fitness.workout.session.dto.SessionDto
import com.jagaldol.behind.fitness.workout.session.dto.SessionRequest
import com.jagaldol.behind.fitness.workout.session.dto.SessionResponse
import com.jagaldol.behind.fitness.workout.session.repository.SessionRepository
import com.jagaldol.behind.fitness.workout.set_record.repository.SetRecordRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Transactional(readOnly = true)
@Service
class SessionService(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val recordRepository: RecordRepository,
    private val setRecordRepository: SetRecordRepository
) {
    @Transactional
    fun create(userId: Long, requestDto: SessionRequest.CreateDto): CreateResponseDto {
        val user = userRepository.findByIdOrNull(userId) ?: throw CustomException(ErrorCode.NOT_FOUND_USER)
        val workoutSession = Session(user, requestDto.date!!, requestDto.startTime, requestDto.endTime)

        return CreateResponseDto(sessionRepository.save(workoutSession).id ?: throw CustomException(ErrorCode.SERVER_ERROR))
    }

    @Transactional
    fun update(sessionId: Long, userId: Long, requestDto: SessionRequest.UpdateDto) {
        val workoutSession = sessionRepository.findByIdOrNull(sessionId) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
        if (workoutSession.user.id != userId) throw CustomException(ErrorCode.PERMISSION_DENIED)

        with(requestDto) {
            date?.let { workoutSession.date = it }
            startTime?.let { workoutSession.startTime = it }
            endTime?.let { workoutSession.endTime = it }
        }
    }

    fun get(userId: Long, page: Int, date: LocalDate?): SessionResponse.GetDto {
        val pageRequest = PageRequest.of(page - 1, 20, Sort.by("date", "startTime").descending())
        val sessions = date?.let {
            sessionRepository.findAllByUserIdAndDate(userId, date, pageRequest)
        } ?: sessionRepository.findAllByUserId(userId, pageRequest)

        val sessionDtos = sessions.map { session ->
            val records = recordRepository.findBySessionId(session.id!!)

            val recordDtos = records.map {
                val setRecords = setRecordRepository.findAllByRecordId(it.id!!)
                RecordDto(it, setRecords)
            }

            SessionDto(session, recordDtos)
        }

        return SessionResponse.GetDto.of(sessionDtos)
    }

    @Transactional
    fun delete(sessionId: Long, userId: Long) {
        val session = sessionRepository.findByIdOrNull(sessionId) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
        if (session.user.id != userId) throw CustomException(ErrorCode.PERMISSION_DENIED)

        val records = recordRepository.findBySessionId(session.id!!)

        val recordDtos = records.forEach {
            setRecordRepository.deleteByRecordId(it.id!!)
            recordRepository.delete(it)
        }
        sessionRepository.delete(session)
    }
}