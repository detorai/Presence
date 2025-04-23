package org.example.presenceapp.domain.repo

import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.entities.Presence
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.entities.Subject

interface ScheduleRepository {
    suspend fun getSchedule(groupCommand: GroupCommand): List<Schedule>
    suspend fun getLocalSchedule(): List<Schedule>

    suspend fun getPresenceByGroup(groupCommand: GroupCommand): List<Presence>

    suspend fun setSubject(subject: Subject)
    suspend fun setSchedule(schedule: Schedule)
}