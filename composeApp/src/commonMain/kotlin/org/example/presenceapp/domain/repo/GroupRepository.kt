package org.example.presenceapp.domain.repo

import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.entities.Presence
import org.example.presenceapp.domain.entities.Schedule
import org.example.presenceapp.domain.entities.Students
import org.example.presenceapp.domain.entities.Subject

interface GroupRepository {
    suspend fun getSchedule(groupCommand: GroupCommand): List<Schedule>
    suspend fun getLocalSchedule(): List<Schedule>

    suspend fun getPresenceByGroup(groupCommand: GroupCommand): List<Presence>

    suspend fun getStudents(groupCommand: GroupCommand): List<Students>


    suspend fun setSubject(subject: Subject)
    suspend fun setSchedule(schedule: Schedule)
}