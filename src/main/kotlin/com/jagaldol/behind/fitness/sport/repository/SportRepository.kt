package com.jagaldol.behind.fitness.sport.repository

import com.jagaldol.behind.fitness.sport.Sport
import org.springframework.data.jpa.repository.JpaRepository

interface SportRepository : JpaRepository<Sport, Long> {
    fun findByNameAndUserId(name: String, userId: Long): Sport?
    fun findAllByUserId(userId: Long): List<Sport>
}