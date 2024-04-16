package com.jagaldol.behind.fitness.workout.record.dto

import com.jagaldol.behind.fitness.workout.record.Record
import com.jagaldol.behind.fitness.workout.set_record.SetRecord

data class RecordDto(
    val record: Record,
    val setRecords: List<SetRecord>
)
