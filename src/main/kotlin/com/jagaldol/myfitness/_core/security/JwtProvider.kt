package com.jagaldol.myfitness._core.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.jagaldol.myfitness._core.errors.exception.CustomException
import com.jagaldol.myfitness._core.errors.exception.ErrorCode
import com.jagaldol.myfitness.user.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId

@Component
class JwtProvider(
    @Value("\${private.token_exp.access}")
    val accessExp: Long,
    @Value("\${private.token_exp.refresh}")
    val refreshExp: Long,
    @Value("\${private.secret}")
    private val tokenSecret: String,
) {
    val tokenPrefix = "Bearer "
    val header = "Authorization"
    val typeAccess = "access"
    val typeRefresh = "refresh"

    fun addPrefix(token: String) = "$tokenPrefix$token"

    fun removePrefix(token: String) = token.replace(tokenPrefix, "")

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
            throw CustomException(ErrorCode.INVALID_TOKEN)
        }
        return decodedJWT
    }

    private fun create(user: User, exp: Long, type: String): String {
        val now = LocalDateTime.now()
        val expired = now.plusSeconds(exp)

        return JWT.create()
            .withSubject(user.id.toString())
            .withClaim("type", type)
            .withExpiresAt(expired.atZone(ZoneId.systemDefault()).toInstant())
            .sign(Algorithm.HMAC512(tokenSecret))
    }

}