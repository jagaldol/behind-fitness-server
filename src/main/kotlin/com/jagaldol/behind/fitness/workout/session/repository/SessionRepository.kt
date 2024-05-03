package com.jagaldol.behind.fitness.workout.session.repository

import com.jagaldol.behind.fitness.workout.session.Session
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface SessionRepository : JpaRepository<Session, Long> {
    fun findAllByUserId(userId: Long, pageRequest: PageRequest): List<Session>
    fun findAllByUserIdAndDate(userId: Long, date: LocalDate, pageRequest: PageRequest): List<Session>

    @Query("SELECT DISTINCT s.date FROM Session s WHERE s.user.id =:userId AND s.date BETWEEN :startDate AND :endDate ORDER BY s.date")
    fun findDistinctDateByUserIdAndDateBetween(userId: Long, startDate: LocalDate, endDate: LocalDate): List<LocalDate>
}