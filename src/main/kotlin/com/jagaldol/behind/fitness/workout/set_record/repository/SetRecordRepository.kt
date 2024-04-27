package com.jagaldol.behind.fitness.workout.set_record.repository

import com.jagaldol.behind.fitness.workout.set_record.SetRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SetRecordRepository : JpaRepository<SetRecord, Long> {
    fun findAllByRecordId(recordId: Long): List<SetRecord>

    @Query("SELECT sr FROM SetRecord sr JOIN FETCH sr.record r JOIN FETCH r.session s WHERE sr.id = :id")
    fun findByIdOrNullFetchSession(id: Long): SetRecord?

    fun deleteByRecordId(id: Long)
}