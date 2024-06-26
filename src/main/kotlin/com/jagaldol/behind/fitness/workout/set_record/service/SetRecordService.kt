package com.jagaldol.behind.fitness.workout.set_record.service

import com.jagaldol.behind.fitness._core.errors.exception.CustomException
import com.jagaldol.behind.fitness._core.errors.exception.ErrorCode
import com.jagaldol.behind.fitness._core.utils.CreateResponseDto
import com.jagaldol.behind.fitness.workout.record.repository.RecordRepository
import com.jagaldol.behind.fitness.workout.set_record.SetRecord
import com.jagaldol.behind.fitness.workout.set_record.dto.SetRecordRequest
import com.jagaldol.behind.fitness.workout.set_record.repository.SetRecordRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SetRecordService(
    private val setRecordRepository: SetRecordRepository,
    private val recordRepository: RecordRepository,
) {
    @Transactional
    fun create(userId: Long, recordId: Long, requestDto: SetRecordRequest.CreateDto): CreateResponseDto {
        val workoutRecord = recordRepository.findByIdOrNullFetchSession(recordId) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
        if (workoutRecord.session.user.id != userId) throw CustomException(ErrorCode.PERMISSION_DENIED)

        val newSetRecord = SetRecord(
            record = workoutRecord,
            weight = requestDto.weight,
            count = requestDto.count,
            countUnit = requestDto.countUnit
        )

        return CreateResponseDto(setRecordRepository.save(newSetRecord).id!!)
    }

    @Transactional
    fun update(userId: Long, setId: Long, requestDto: SetRecordRequest.UpdateDto) {
        val setRecord = setRecordRepository.findByIdOrNullFetchSession(setId) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
        if (setRecord.record.session.user.id != userId) throw CustomException(ErrorCode.PERMISSION_DENIED)

        with(requestDto) {

            weight?.let { setRecord.weight = it }
            count?.let { setRecord.count = it }
            countUnit?.let { setRecord.countUnit = it }
        }
    }

    @Transactional
    fun delete(userId: Long, setId: Long) {
        val setRecord = setRecordRepository.findByIdOrNullFetchSession(setId) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
        if (setRecord.record.session.user.id != userId) throw CustomException(ErrorCode.PERMISSION_DENIED)

        setRecordRepository.delete(setRecord)
    }
}