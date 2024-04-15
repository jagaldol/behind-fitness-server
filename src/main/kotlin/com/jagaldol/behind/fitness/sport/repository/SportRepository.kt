package com.jagaldol.behind.fitness.sport.repository

import com.jagaldol.behind.fitness.sport.Sport
import org.springframework.data.jpa.repository.JpaRepository

interface SportRepository : JpaRepository<Sport, Long> {
    fun findByName(name: String): Sport?
    fun findAllByUserId(userId: Long): List<Sport>
}