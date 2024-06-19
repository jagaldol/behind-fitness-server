package com.jagaldol.behind.fitness.user

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import java.time.LocalDateTime


@Entity
@DynamicInsert
@Table(name = "user_tb")
class User(
    @Column(length = 20, nullable = false)
    var name: String,
    @Column(length = 100, nullable = false, unique = true)
    var email: String,
    @Column(length = 100, nullable = false)
    var password: String,
    @Column(length = 100)
    var memo: String,
    @ColumnDefault("0")
    var gender: Byte? = null,
    @ColumnDefault("170")
    var height: Double? = null,
    @ColumnDefault("now()")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)