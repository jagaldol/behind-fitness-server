package com.jagaldol.behind.fitness.workout.session.dto

import com.jagaldol.behind.fitness.workout.record.dto.RecordDto
import com.jagaldol.behind.fitness.workout.session.Session

data class SessionDto(
    val session: Session,
    val records: List<RecordDto>
)