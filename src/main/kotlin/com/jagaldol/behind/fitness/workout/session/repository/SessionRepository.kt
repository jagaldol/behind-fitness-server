package com.jagaldol.behind.fitness.workout.session.repository

import com.jagaldol.behind.fitness.workout.session.Session
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<Session, Long>