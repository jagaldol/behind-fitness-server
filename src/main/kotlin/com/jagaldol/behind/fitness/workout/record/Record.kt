package com.jagaldol.behind.fitness.workout.record

import com.jagaldol.behind.fitness.sport.Sport
import com.jagaldol.behind.fitness.workout.session.Session
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import java.time.LocalDateTime

@Entity
@DynamicInsert
@Table(name = "workout_record_tb")
class Record(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_session_id")
    val session: Session,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id")
    var sport: Sport,
    @ColumnDefault("now()")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)