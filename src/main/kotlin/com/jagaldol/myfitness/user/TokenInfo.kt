package com.jagaldol.myfitness.user

import java.io.Serializable
import java.time.LocalDateTime

data class TokenInfo(
    val ip: String,
    val date: LocalDateTime
) : Serializable {
    companion object {
        const val serialVersionUID = 1L
    }
}
