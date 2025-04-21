package org.example.presenceapp.domain.repo

import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.entities.Schedule

interface ScheduleRepository {
    suspend fun getSchedule(groupCommand: GroupCommand): List<Schedule>
}