package com.jagaldol.behind.fitness.workout.set_record.dto

import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size

class SetRecordRequest {
    data class CreateDto(
        val weight: Double?,
        @field:PositiveOrZero
        val count: Int?,
        @field:Size(min = 1, max = 5, message = "최대 5자까지 입력 가능합니다.")
        val countUnit: String?
    )

    data class UpdateDto(
        val weight: Double?,
        @field:PositiveOrZero
        val count: Int?,
        @field:Size(min = 1, max = 5, message = "최대 5자까지 입력 가능합니다.")
        val countUnit: String?
    )
}