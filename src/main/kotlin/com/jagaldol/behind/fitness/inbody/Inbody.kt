package com.jagaldol.behind.fitness.inbody

import com.jagaldol.behind.fitness.user.User
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@DynamicInsert
@Table(name = "inbody_tb")
class Inbody(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
    @Column(nullable = false)
    var date: LocalDate,
    @ColumnDefault("0")
    var weight: Double? = .0,
    @ColumnDefault("0")
    var muscle: Double? = .0,
    @ColumnDefault("0")
    var fat: Double? = .0,
    @ColumnDefault("0")
    var percentFat: Double? = .0,
    @ColumnDefault("now()")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)