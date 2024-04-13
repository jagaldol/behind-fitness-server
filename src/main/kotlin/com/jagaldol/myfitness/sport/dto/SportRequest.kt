package com.jagaldol.myfitness.sport.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

class SportRequest {
    data class CreateDto(
        @field:NotEmpty @field:Size(max = 20, message = "최대 20자까지 입력 가능합니다.")
        val name: String?
    )
}