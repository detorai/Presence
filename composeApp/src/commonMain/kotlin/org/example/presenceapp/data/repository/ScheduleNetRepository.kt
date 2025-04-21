package org.example.presenceapp.data.repository

import org.example.presenceapp.data.common.toDto
import org.example.presenceapp.data.common.toEntity
import org.example.presenceapp.data.remote.impl.ScheduleApiImpl
import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.repo.ScheduleRepository

class ScheduleNetRepository(
    private val scheduleApiImpl: ScheduleApiImpl,
): ScheduleRepository {

    override suspend fun getSchedule(groupCommand: GroupCommand): List<Schedule> {
        val result = scheduleApiImpl.getSchedule(groupCommand.toDto())
        return result.map { it.toEntity() }
    }
}