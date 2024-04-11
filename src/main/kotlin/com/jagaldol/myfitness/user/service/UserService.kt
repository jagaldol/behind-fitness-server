package com.jagaldol.myfitness.user.service

import com.jagaldol.myfitness._core.errors.exception.CustomException
import com.jagaldol.myfitness._core.errors.exception.ErrorCode
import com.jagaldol.myfitness._core.security.JwtProvider
import com.jagaldol.myfitness.user.TokenInfo
import com.jagaldol.myfitness.user.User
import com.jagaldol.myfitness.user.dto.UserRequest
import com.jagaldol.myfitness.user.dto.UserResponse
import com.jagaldol.myfitness.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit


@Transactional(readOnly = true)
@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
    private val redisTemplate: RedisTemplate<String, Any>,
    @Value("\${private.multi-token-limit}")
    private val tokenLimit: Int
) {
    fun login(requestDto: UserRequest.LoginDto, ip: String): Pair<String, String> {
        val user = userRepository.findByEmail(requestDto.email!!) ?: throw CustomException(ErrorCode.LOGIN_FAILED)
        if (!passwordEncoder.matches(requestDto.password, user.password)) throw CustomException(ErrorCode.LOGIN_FAILED)
        return issueTokens(user, ip)
    }

    fun reIssueTokens(refreshToken: String, ip: String): Pair<String, String> {
        val decodedRefreshToken = jwtProvider.verify(refreshToken, jwtProvider.typeRefresh)

        if (!redisTemplate.opsForHash<String, TokenInfo>().hasKey(decodedRefreshToken.subject, refreshToken)) throw CustomException(
            ErrorCode.INVALID_TOKEN
        )

        val user = userRepository.findByIdOrNull(decodedRefreshToken.subject.toLong()) ?: throw CustomException(ErrorCode.NOT_FOUND_USER)

        return issueTokens(user, ip, refreshToken)
    }

    private fun issueTokens(user: User, ip: String, prevToken: String? = null): Pair<String, String> {
        val access = jwtProvider.createAccess(user)
        val refresh = jwtProvider.createRefresh(user)

        prevToken?.let { redisTemplate.opsForHash<String, TokenInfo>().delete(user.id.toString(), prevToken) }
        val hashOperations = redisTemplate.boundHashOps<String, TokenInfo>(user.id.toString())
        val size = hashOperations.size()?.toInt() ?: 0

        if (size >= tokenLimit) {
            hashOperations.entries()
                ?.toList()
                ?.sortedBy { it.second.date }
                ?.subList(0, size - tokenLimit + 1)
                ?.forEach {
                    hashOperations.delete(it.first)
                }
        }

        hashOperations.put(refresh, TokenInfo(ip, LocalDateTime.now()))
        hashOperations.expire(jwtProvider.refreshExp, TimeUnit.SECONDS)
        return jwtProvider.addPrefix(access) to refresh
    }

    fun logout(userId: Long, refreshToken: String?) {
        refreshToken?.let { redisTemplate.opsForHash<String, TokenInfo>().delete(userId.toString(), refreshToken) }
    }

    fun getMyInfo(userId: Long) =
        UserResponse.GetMyInfoDto(userRepository.findByIdOrNull(userId) ?: throw CustomException(ErrorCode.NOT_FOUND_USER))

    @Transactional
    fun updateMyInfo(userId: Long, requestDto: UserRequest.UpdateMyInfoDto) {
        val user = userRepository.findByIdOrNull(userId) ?: throw CustomException(ErrorCode.NOT_FOUND_USER)
        with(requestDto) {
            name?.let { user.name = it }
            password?.let { user.password = passwordEncoder.encode(it) }
            memo?.let { user.memo = it }
        }
    }
}