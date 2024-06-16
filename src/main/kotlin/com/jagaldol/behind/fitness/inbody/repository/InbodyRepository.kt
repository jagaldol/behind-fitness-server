package com.jagaldol.behind.fitness.inbody.repository

import com.jagaldol.behind.fitness.inbody.Inbody
import org.springframework.data.jpa.repository.JpaRepository

interface InbodyRepository : JpaRepository<Inbody, Long> {
    fun findByUserIdOrderByDate(userId: Long): List<Inbody>
}