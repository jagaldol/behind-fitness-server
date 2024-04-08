package com.jagaldol.myfitness.user

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import java.time.LocalDateTime


@Entity
@DynamicInsert
@Table(name = "user_tb")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(length = 20, nullable = false)
    var name: String,
    @Column(length = 100, nullable = false, unique = true)
    var email: String,
    @Column(length = 100, nullable = false)
    var password: String,
    @ColumnDefault("now()")
    val createdAt: LocalDateTime = LocalDateTime.now()
)