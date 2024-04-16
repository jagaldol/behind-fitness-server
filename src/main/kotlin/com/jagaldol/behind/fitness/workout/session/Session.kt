package com.jagaldol.behind.fitness.workout.session

import com.jagaldol.behind.fitness.user.User
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity
@DynamicInsert
@Table(name = "workout_session_tb")
class Session(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
    @Column(nullable = false)
    var date: LocalDate,
    @Column
    var startTime: LocalTime? = null,
    @Column
    var endTime: LocalTime? = null,
    @ColumnDefault("now()")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)