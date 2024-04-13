package com.jagaldol.myfitness.sport.service

import com.jagaldol.myfitness._core.errors.exception.CustomException
import com.jagaldol.myfitness._core.errors.exception.ErrorCode
import com.jagaldol.myfitness._core.utils.CreateResponseDto
import com.jagaldol.myfitness.sport.Sport
import com.jagaldol.myfitness.sport.dto.SportRequest
import com.jagaldol.myfitness.sport.repository.SportRepository
import com.jagaldol.myfitness.user.repository.UserRepository
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
}