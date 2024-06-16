package com.jagaldol.behind.fitness.inbody.dto

import com.jagaldol.behind.fitness.inbody.Inbody
import java.time.LocalDate

class InbodyResponse {
    data class InbodyDto(
        val id: Long,
        val date: LocalDate,
        val weight: Double,
        val muscle: Double,
        val fat: Double,
        val percentFat: Double
    ) {
        constructor(inbody: Inbody) : this(inbody.id!!, inbody.date, inbody.weight!!, inbody.muscle!!, inbody.fat!!, inbody.percentFat!!)
    }
}