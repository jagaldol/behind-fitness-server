package com.jagaldol.behind.fitness.workout.record.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive


class RecordRequest {
    data class CreateDto(
        @field:NotNull @field:Positive
        val sportId: Long?
    )
}