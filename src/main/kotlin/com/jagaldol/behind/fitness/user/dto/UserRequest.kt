package com.jagaldol.behind.fitness.user.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

class UserRequest {
    data class LoginDto(
        @field:NotEmpty @field:Size(max = 100, message = "최대 100자까지 입력 가능합니다.")
        val email: String?,
        @field:NotEmpty @field:Size(max = 64, message = "최대 64자까지 입력 가능합니다.")
        val password: String?
    )

    data class UpdateMyInfoDto(
        @field:Size(min = 1, max = 20, message = "1 ~ 20자 이내로 입력해주세요.")
        val name: String?,
        @field:Size(max = 100, message = "최대 100자까지 입력 가능합니다.")
        val memo: String?,
        @field:Size(min = 4, max = 64, message = "4 ~ 64자 이내로 입력해주세요.")
        val password: String?,
        val gender: Boolean?,
        val height: Double?,
    )
}
