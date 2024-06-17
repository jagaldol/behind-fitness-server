package com.jagaldol.behind.fitness.inbody.service

import com.jagaldol.behind.fitness._core.errors.exception.CustomException
import com.jagaldol.behind.fitness._core.errors.exception.ErrorCode
import com.jagaldol.behind.fitness._core.utils.CreateResponseDto
import com.jagaldol.behind.fitness.inbody.Inbody
import com.jagaldol.behind.fitness.inbody.dto.InbodyRequest
import com.jagaldol.behind.fitness.inbody.dto.InbodyResponse
import com.jagaldol.behind.fitness.inbody.repository.InbodyRepository
import com.jagaldol.behind.fitness.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class InbodyService(
    private val userRepository: UserRepository,
    private val inbodyRepository: InbodyRepository
) {
    fun get(userId: Long) = inbodyRepository.findByUserIdOrderByDate(userId).map { InbodyResponse.InbodyDto(it) }

    @Transactional
    fun create(userId: Long, requestDto: InbodyRequest.CreateDto): CreateResponseDto {
        val user = userRepository.findByIdOrNull(userId) ?: throw CustomException(ErrorCode.NOT_FOUND_USER)
        val inbody = Inbody(user, requestDto.date!!, requestDto.weight, requestDto.muscle, requestDto.fat, requestDto.percentFat)

        return CreateResponseDto(inbodyRepository.save(inbody).id!!)
    }

    @Transactional
    fun delete(inbodyId: Long, userId: Long) {
        val inbody = inbodyRepository.findByIdOrNull(inbodyId) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
        if (inbody.user.id != userId) throw CustomException(ErrorCode.PERMISSION_DENIED)

        inbodyRepository.delete(inbody)
    }

    @Transactional
    fun update(inbodyId: Long, userId: Long, requestDto: InbodyRequest.UpdateDto) {
        val inbody = inbodyRepository.findByIdOrNull(inbodyId) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
        if (inbody.user.id != userId) throw CustomException(ErrorCode.PERMISSION_DENIED)

        with(requestDto) {
            date?.let { inbody.date = it }
            weight?.let { inbody.weight = it }
            muscle?.let { inbody.muscle = it }
            fat?.let { inbody.fat = it }
            percentFat?.let { inbody.percentFat = it }
        }
    }
}