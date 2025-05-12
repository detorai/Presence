package org.example.presenceapp.data.repository

import org.example.presenceapp.data.common.toDto
import org.example.presenceapp.data.common.toEntity
import org.example.presenceapp.data.local.sql.cache.Database
import org.example.presenceapp.data.local.sql.cache.DatabaseDriverFactory
import org.example.presenceapp.data.remote.impl.GroupApiImpl
import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.entities.Presence
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.entities.Students
import org.example.presenceapp.domain.entities.Subject
import org.example.presenceapp.domain.repo.GroupRepository

class GroupNetRepository(
    private val groupApiImpl: GroupApiImpl,
    databaseDriverFactory: DatabaseDriverFactory,
): GroupRepository {

    private val database = Database(databaseDriverFactory)

    override suspend fun getSchedule(groupCommand: GroupCommand): List<Schedule> {
        val result = groupApiImpl.getSchedule(groupCommand.toDto())
        return result.map { it.toEntity() }
    }

    override suspend fun getLocalSchedule(): List<Schedule> {
        return database.getAllSchedules()
    }


    override suspend fun getPresenceByGroup(groupCommand: GroupCommand): List<Presence> {
        val result = groupApiImpl.getPresence(groupCommand.toDto())
        return result.map { it.toEntity() }
    }

    override suspend fun getStudents(groupCommand: GroupCommand): List<Students> {
        val result = groupApiImpl.getStudents(groupCommand.toDto())
        return result.map { it.toEntity() }
    }

    override suspend fun saveSchedule(schedule: List<Schedule>) {
        schedule.forEach { daySchedule ->
            database.setSchedule(daySchedule)
        }
    }

    override suspend fun saveSubject(subject: Subject) {
        database.setSubject(subject)
    }
}