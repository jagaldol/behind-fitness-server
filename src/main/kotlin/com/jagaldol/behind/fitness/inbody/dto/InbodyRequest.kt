package com.jagaldol.behind.fitness.inbody.dto

import jakarta.validation.constraints.NotNull
import java.time.LocalDate

class InbodyRequest {
    data class CreateDto(
        @field:NotNull
        val date: LocalDate?,
        val weight: Double?,
        val muscle: Double?,
        val fat: Double?,
        val percentFat: Double?
    )
}