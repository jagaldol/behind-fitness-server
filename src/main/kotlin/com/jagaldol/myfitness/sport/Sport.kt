package com.jagaldol.myfitness.sport

import com.jagaldol.myfitness.user.User
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicInsert
import java.time.LocalDateTime

@Entity
@DynamicInsert
@Table(
    name = "sport_tb", uniqueConstraints = [
        UniqueConstraint(columnNames = ["user_id", "name"])
    ]
)
class Sport(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
    @Column(length = 20, nullable = false, unique = true)
    var name: String,
    @ColumnDefault("now()")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)