package com.jagaldol.myfitness._core.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.jagaldol.myfitness._core.config.CORSConfig
import com.jagaldol.myfitness._core.errors.exception.CustomException
import com.jagaldol.myfitness._core.errors.exception.ErrorCode
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtAuthenticationFilter: JwtAuthenticationFilter, private val corsConfig: CORSConfig) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {

        httpSecurity
            .csrf { it.disable() }
            .headers { it.frameOptions { options -> options.sameOrigin() } }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint { _, response, _ ->
                    response.run {
                        val e = CustomException(ErrorCode.UNAUTHORIZED)
                        status = e.status()
                        contentType = "application/json; charset=utf-8"
                        writer.println(ObjectMapper().writeValueAsString(e.body()))
                    }
                }
                it.accessDeniedHandler { _, response, _ ->
                    response.run {
                        val e = CustomException(ErrorCode.PERMISSION_DENIED)
                        status = e.status()
                        contentType = "application/json; charset=utf-8"
                        writer.println(ObjectMapper().writeValueAsString(e.body()))
                    }
                }
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers(AntPathRequestMatcher("/users/**")).hasRole("USER")
                    .anyRequest().permitAll()
            }




        return httpSecurity.build()
    }

    fun configurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            addAllowedHeader("*")
            addAllowedMethod("*") // GET, POST, PUT, DELETE
            allowedOrigins = corsConfig.CORS
            allowCredentials = true
            addExposedHeader("Authorization")
        }
        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", configuration)
        }
    }
}