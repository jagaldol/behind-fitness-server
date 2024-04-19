package com.jagaldol.behind.fitness._core.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class CORSConfig(@Value("\${private.domain}") private val domain: String) {
    val cors
        get() = listOf(domain)
}