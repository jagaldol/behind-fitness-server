package com.jagaldol.behind.fitness.user.dto

import com.jagaldol.behind.fitness.user.User

class UserResponse {
    data class GetMyInfoDto(
        val id: Long,
        val name: String,
        val email: String,
        val memo: String?
    ) {
        constructor(user: User) : this(user.id!!, user.name, user.email, user.memo)
    }
}