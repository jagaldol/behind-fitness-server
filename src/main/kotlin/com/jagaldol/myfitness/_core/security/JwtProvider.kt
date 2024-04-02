package com.jagaldol.myfitness._core.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.jagaldol.myfitness.user.User
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class JwtProvider {
    companion object {
        const val TOKEN_PREFIX = "Bearer "
        const val HEADER = "Authorization"
        const val TYPE_ACCESS = "access"
        const val TYPE_REFRESH = "refresh"
        private const val ACCESS_EXP = 600L
        private const val REFRESH_EXP = 2592000L
        private const val TOKEN_SECRET = "fitness"

        fun createAccess(user: User): String {
            return create(user, ACCESS_EXP, TYPE_ACCESS)
        }

        fun createRefresh(user: User): String {
            return create(user, REFRESH_EXP, TYPE_REFRESH)
        }

        fun verify(jwt: String, type: String): DecodedJWT {
            val decodedJWT = JWT.require(Algorithm.HMAC512(TOKEN_SECRET)).build()
                    .verify(jwt)

            if (decodedJWT.getClaim("type").asString() != type) {
                throw RuntimeException("토큰 검증 실패")
            }
            return decodedJWT
        }

        private fun create(user: User, exp: Long, type: String): String {
            val now = LocalDateTime.now()
            val expired = now.plusSeconds(exp)
            val jwt = JWT.create()
                    .withClaim("type", type)
                    .sign(Algorithm.HMAC512(TOKEN_SECRET))

            return StringBuilder(TOKEN_PREFIX).append(jwt).toString()
        }
    }

}