package com.jagaldol.behind.fitness.workout.set_record.repository

import com.jagaldol.behind.fitness.workout.set_record.SetRecord
import org.springframework.data.jpa.repository.JpaRepository

interface SetRecordRepository : JpaRepository<SetRecord, Long> {
    fun findAllByRecordId(recordId: Long): List<SetRecord>
}