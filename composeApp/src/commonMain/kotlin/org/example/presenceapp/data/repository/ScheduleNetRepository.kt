package org.example.presenceapp.data.repository

import org.example.presenceapp.data.common.toDto
import org.example.presenceapp.data.common.toEntity
import org.example.presenceapp.data.local.sql.cache.Database
import org.example.presenceapp.data.local.sql.cache.DatabaseDriverFactory
import org.example.presenceapp.data.remote.impl.ScheduleApiImpl
import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.entities.Presence
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.entities.Subject
import org.example.presenceapp.domain.repo.ScheduleRepository

class ScheduleNetRepository(
    private val scheduleApiImpl: ScheduleApiImpl,
    databaseDriverFactory: DatabaseDriverFactory,
): ScheduleRepository {

    private val database = Database(databaseDriverFactory)

    override suspend fun getSchedule(groupCommand: GroupCommand): List<Schedule> {
        val result = scheduleApiImpl.getSchedule(groupCommand.toDto())
        return result.map { it.toEntity() }
    }

    override suspend fun getLocalSchedule(): List<Schedule> {
        return database.getAllSchedule()
    }

    override suspend fun getPresenceByGroup(groupCommand: GroupCommand): List<Presence> {
        val result = scheduleApiImpl.getPresence(groupCommand.toDto())
        return result.map { it.toEntity() }
    }

    override suspend fun setSubject(subject: Subject) {
        database.setSubjects(subject)
    }

    override suspend fun setSchedule(schedule: Schedule) {
        database.setSchedule(schedule)
    }
}