package com.jagaldol.myfitness.sport.dto

import com.jagaldol.myfitness.sport.Sport

class SportResponse {
    data class GetDto(
        val sports: List<SportDto>
    ) {
        companion object {
            fun of(sports: List<Sport>) = GetDto(sports.map { SportDto(it.id!!, it.name) })
        }

        data class SportDto(
            val id: Long,
            val name: String
        )
    }
}