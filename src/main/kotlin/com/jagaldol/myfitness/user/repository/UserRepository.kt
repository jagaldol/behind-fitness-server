package com.jagaldol.myfitness.user.repository

import com.jagaldol.myfitness.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}