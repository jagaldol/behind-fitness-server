package com.jagaldol.myfitness.user.service

import com.jagaldol.myfitness._core.errors.exception.CustomException
import com.jagaldol.myfitness._core.errors.exception.ErrorCode
import com.jagaldol.myfitness._core.security.JwtProvider
import com.jagaldol.myfitness.user.User
import com.jagaldol.myfitness.user.dto.UserRequest
import com.jagaldol.myfitness.user.repository.UserRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit


@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
    private val redisTemplate: RedisTemplate<String, String>
) {
    fun login(requestDto: UserRequest.LoginDto): Pair<String, String> {
        val user = userRepository.findByEmail(requestDto.email) ?: throw CustomException(ErrorCode.LOGIN_FAILED)
        if (!passwordEncoder.matches(requestDto.password, user.password)) throw CustomException(ErrorCode.LOGIN_FAILED)

        return issueTokens(user)
    }

    private fun issueTokens(user: User): Pair<String, String> {
        val access = jwtProvider.createAccess(user)
        val refresh = jwtProvider.createRefresh(user)
        redisTemplate.opsForValue().set(
            user.id.toString(),
            refresh,
            jwtProvider.refreshExp,
            TimeUnit.SECONDS
        )
        return Pair<String, String>(jwtProvider.addPrefix(access), refresh)
    }
}