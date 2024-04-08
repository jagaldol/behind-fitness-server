package com.jagaldol.myfitness._core.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class CORSConfig(@Value("\${private.domain}") private val domain: String) {
    val CORS = listOf(
        "http://localhost:3000",
        "http://127.0.0.1:3000",
    )
        get() = field + domain
}