package com.jagaldol.behind.fitness.workout.record.repository

import com.jagaldol.behind.fitness.workout.record.Record
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RecordRepository : JpaRepository<Record, Long> {
    fun findBySessionId(sessionId: Long): List<Record>

    @Query("SELECT r FROM Record r JOIN FETCH r.session s WHERE r.id = :id")
    fun findByIdOrNullFetchSession(id: Long): Record?
}