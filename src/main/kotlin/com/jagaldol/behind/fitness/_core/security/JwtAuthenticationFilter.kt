package com.jagaldol.behind.fitness._core.security

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

private val log = KotlinLogging.logger {}

@Component
class JwtAuthenticationFilter(private val jwtProvider: JwtProvider) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val jwt = request.getHeader(jwtProvider.header)

        jwt?.let {
            try {
                val decodedJWT = jwtProvider.verify(jwtProvider.removePrefix(jwt), jwtProvider.typeAccess)
                val id = decodedJWT.subject.toLong()

                val userDetails = CustomUserDetails(userId = id)

                val authentication: Authentication = UsernamePasswordAuthenticationToken(
                    userDetails,
                    userDetails.password,
                    userDetails.authorities
                )
                SecurityContextHolder.getContext().authentication = authentication
            } catch (e: Exception) {
                log.debug { "인증 실패" }
            }
        }

        chain.doFilter(request, response)
    }


}