package com.jagaldol.behind.fitness.workout.session.dto

import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.time.LocalTime

class SessionRequest {
    data class CreateDto(
        @field:NotNull
        val date: LocalDate?,
        val startTime: LocalTime?,
        val endTime: LocalTime?
    )
}