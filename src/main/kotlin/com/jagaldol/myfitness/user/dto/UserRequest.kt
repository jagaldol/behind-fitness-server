package com.jagaldol.myfitness.user.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

class UserRequest {
    data class LoginDto(
        @NotEmpty @Size(max = 100, message = "최대 100자까지 입력 가능합니다.")
        val email: String,
        @NotEmpty @Size(max = 64, message = "최대 64자까지 입력 가능합니다.")
        val password: String
    )
}

