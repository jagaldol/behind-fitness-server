package com.jagaldol.behind.fitness.workout.record.repository

import com.jagaldol.behind.fitness.workout.record.Record
import org.springframework.data.jpa.repository.JpaRepository

interface RecordRepository : JpaRepository<Record, Long> {
    fun findBySessionId(sessionId: Long): List<Record>
}