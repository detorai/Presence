package org.example.presenceapp.data.repository

import org.example.presenceapp.data.common.toEntity
import org.example.presenceapp.data.remote.impl.ScheduleApiImpl
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.repo.ScheduleRepository

class ScheduleRepository(
    private val scheduleApiImpl: ScheduleApiImpl,
): ScheduleRepository {
    override suspend fun getSchedule(group_id: Int): List<Schedule> {
        val result = scheduleApiImpl.getSchedule(group_id)
        return result.map { it.toEntity() }
    }
}