package com.jagaldol.behind.fitness.workout.session.dto

import com.jagaldol.behind.fitness.sport.Sport
import com.jagaldol.behind.fitness.workout.record.dto.RecordDto
import com.jagaldol.behind.fitness.workout.set_record.SetRecord
import java.time.LocalDate
import java.time.LocalTime

class SessionResponse {
    data class GetDto(
        val sessions: List<GetSessionDto>
    ) {
        companion object {
            fun of(sessionDtos: List<SessionDto>): GetDto {

                return GetDto(sessionDtos.map { GetSessionDto(it) })
            }
        }

        data class GetSessionDto(
            val id: Long,
            val date: LocalDate,
            val startTime: LocalTime?,
            val endTime: LocalTime?,
            val records: List<GetRecordDto>
        ) {
            constructor(dto: SessionDto) : this(
                id = dto.session.id!!,
                date = dto.session.date,
                startTime = dto.session.startTime,
                endTime = dto.session.endTime,
                records = dto.records.map { GetRecordDto(it) }
            )

            data class GetRecordDto(
                val id: Long,
                val sport: SportDto,
                val sets: List<GetSetDto>
            ) {
                constructor(dto: RecordDto) : this(
                    id = dto.record.id!!,
                    sport = SportDto(dto.record.sport),
                    sets = dto.setRecords.map { GetSetDto(it) }
                )

                data class SportDto(
                    val id: Long,
                    val name: String
                ) {
                    constructor(sport: Sport) : this(sport.id!!, sport.name)
                }

                data class GetSetDto(
                    val id: Long,
                    val weight: Int,
                    val count: Int,
                    val countUnit: String
                ) {
                    constructor(setRecord: SetRecord) : this(
                        setRecord.id!!,
                        setRecord.weight!!,
                        setRecord.count!!,
                        setRecord.countUnit!!
                    )
                }
            }
        }
    }

    data class GetByIdDto(
        val session: GetSessionDto
    ) {
        constructor(sessionDto: SessionDto) : this(GetSessionDto(sessionDto))

        data class GetSessionDto(
            val id: Long,
            val date: LocalDate,
            val startTime: LocalTime?,
            val endTime: LocalTime?,
            val records: List<GetRecordDto>
        ) {
            constructor(dto: SessionDto) : this(
                id = dto.session.id!!,
                date = dto.session.date,
                startTime = dto.session.startTime,
                endTime = dto.session.endTime,
                records = dto.records.map { GetRecordDto(it) }
            )

            data class GetRecordDto(
                val id: Long,
                val sport: SportDto,
                val sets: List<GetSetDto>
            ) {
                constructor(dto: RecordDto) : this(
                    id = dto.record.id!!,
                    sport = SportDto(dto.record.sport),
                    sets = dto.setRecords.map { GetSetDto(it) }
                )

                data class SportDto(
                    val id: Long,
                    val name: String
                ) {
                    constructor(sport: Sport) : this(sport.id!!, sport.name)
                }

                data class GetSetDto(
                    val id: Long,
                    val weight: Int,
                    val count: Int,
                    val countUnit: String
                ) {
                    constructor(setRecord: SetRecord) : this(
                        setRecord.id!!,
                        setRecord.weight!!,
                        setRecord.count!!,
                        setRecord.countUnit!!
                    )
                }
            }
        }
    }

    data class GetDatesDto(
        val dates: List<LocalDate>
    )
}