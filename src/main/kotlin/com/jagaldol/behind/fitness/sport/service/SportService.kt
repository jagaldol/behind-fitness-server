package com.jagaldol.behind.fitness.sport.service

import com.jagaldol.behind.fitness._core.errors.exception.CustomException
import com.jagaldol.behind.fitness._core.errors.exception.ErrorCode
import com.jagaldol.behind.fitness._core.utils.CreateResponseDto
import com.jagaldol.behind.fitness.sport.Sport
import com.jagaldol.behind.fitness.sport.dto.SportRequest
import com.jagaldol.behind.fitness.sport.dto.SportResponse
import com.jagaldol.behind.fitness.sport.repository.SportRepository
import com.jagaldol.behind.fitness.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class SportService(
    private val sportRepository: SportRepository,
    private val userRepository: UserRepository
) {
    @Transactional
    fun create(userId: Long, requestDto: SportRequest.CreateDto): CreateResponseDto {
        sportRepository.findByName(requestDto.name!!)?.let { throw CustomException(ErrorCode.DUPLICATED_DATA) }
        val user = userRepository.findByIdOrNull(userId) ?: throw CustomException(ErrorCode.NOT_FOUND_USER)
        return CreateResponseDto(
            sportRepository.save(Sport(user, requestDto.name)).id
                ?: throw CustomException(ErrorCode.SERVER_ERROR)
        )
    }

    fun get(userId: Long) = SportResponse.GetDto.of(sportRepository.findAllByUserId(userId))

    @Transactional
    fun update(sportId: Long, userId: Long, requestDto: SportRequest.UpdateDto) {
        val sport = sportRepository.findByIdOrNull(sportId) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
        if (sport.user.id != userId) throw CustomException(ErrorCode.PERMISSION_DENIED)

        requestDto.name?.let { sport.name = it }
    }

    @Transactional
    fun delete(sportId: Long, userId: Long) {
        val sport = sportRepository.findByIdOrNull(sportId) ?: throw CustomException(ErrorCode.NOT_FOUND_DATA)
        if (sport.user.id != userId) throw CustomException(ErrorCode.PERMISSION_DENIED)

        sportRepository.delete(sport)
    }
}