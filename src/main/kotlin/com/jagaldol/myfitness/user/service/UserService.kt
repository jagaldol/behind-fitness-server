package com.jagaldol.myfitness.user.service

import com.jagaldol.myfitness._core.errors.exception.CustomException
import com.jagaldol.myfitness._core.errors.exception.ErrorCode
import com.jagaldol.myfitness._core.security.JwtProvider
import com.jagaldol.myfitness.user.TokenInfo
import com.jagaldol.myfitness.user.User
import com.jagaldol.myfitness.user.dto.UserRequest
import com.jagaldol.myfitness.user.repository.UserRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
    private val redisTemplate: RedisTemplate<String, Any>
) {
    fun login(requestDto: UserRequest.LoginDto): Pair<String, String> {
        val user = userRepository.findByEmail(requestDto.email) ?: throw CustomException(ErrorCode.LOGIN_FAILED)
        if (!passwordEncoder.matches(requestDto.password, user.password)) throw CustomException(ErrorCode.LOGIN_FAILED)

        return issueTokens(user)
    }

    fun reIssueTokens(refreshToken: String): Pair<String, String> {
        val decodedRefreshToken = jwtProvider.verify(refreshToken, jwtProvider.typeRefresh)

        if (!redisTemplate.opsForHash<String, TokenInfo>().hasKey(decodedRefreshToken.subject, refreshToken)) throw CustomException(
            ErrorCode.INVALID_TOKEN
        )

        val user = userRepository.findByIdOrNull(decodedRefreshToken.subject.toLong()) ?: throw CustomException(ErrorCode.NOT_FOUND_USER)

        return issueTokens(user, refreshToken)
    }

    private fun issueTokens(user: User, prevToken: String? = null): Pair<String, String> {
        val access = jwtProvider.createAccess(user)
        val refresh = jwtProvider.createRefresh(user)

        prevToken?.let { redisTemplate.opsForHash<String, TokenInfo>().delete(user.id.toString(), prevToken) }
        // TODO: 이전 토큰 없는 경우 개수 검사해서 최대 5개 남기기
        // TODO: 만료된 토큰 삭제하기
        // TODO: ip 가져오기
        redisTemplate.opsForHash<String, TokenInfo>().put(
            user.id.toString(),
            refresh,
            TokenInfo("fsaf", LocalDateTime.now())
        )
        return jwtProvider.addPrefix(access) to refresh
    }

    fun logout(userId: Long, refreshToken: String?) {
        refreshToken?.let { redisTemplate.opsForHash<String, TokenInfo>().delete(userId.toString(), refreshToken) }
    }
}