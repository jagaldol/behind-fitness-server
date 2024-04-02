package com.jagaldol.myfitness._core.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.jagaldol.myfitness.user.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class JwtProvider(
    @Value("\${private.token_exp.access}")
    private var accessExp: Long,
    @Value("\${private.token_exp.refresh}")
    private var refreshExp: Long,
    @Value("\${private.secret}")
    private var tokenSecret: String,
) {
    val tokenPrefix = "Bearer "
    val header = "Authorization"
    val typeAccess = "access"
    val typeRefresh = "refresh"

    fun createAccess(user: User): String {
        return create(user, accessExp, typeAccess)
    }

    fun createRefresh(user: User): String {
        return create(user, refreshExp, typeRefresh)
    }

    fun verify(jwt: String, type: String): DecodedJWT {
        val decodedJWT = JWT.require(Algorithm.HMAC512(tokenSecret)).build()
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
            .sign(Algorithm.HMAC512(tokenSecret))

        return StringBuilder(tokenPrefix).append(jwt).toString()
    }

}