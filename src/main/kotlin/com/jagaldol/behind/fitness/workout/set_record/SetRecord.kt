package com.jagaldol.behind.fitness.workout.set_record

import com.jagaldol.behind.fitness.workout.record.Record
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import java.time.LocalDateTime

@Entity
@DynamicInsert
@Table(name = "set_record_tb")
class SetRecord(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_record_id")
    val record: Record,
    @ColumnDefault("0")
    var weight: Int? = 0,
    @ColumnDefault("0")
    var count: Int? = 0,
    @Column(length = 5) @ColumnDefault("'회'")
    var countUnit: String? = "회",
    @ColumnDefault("now()")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)