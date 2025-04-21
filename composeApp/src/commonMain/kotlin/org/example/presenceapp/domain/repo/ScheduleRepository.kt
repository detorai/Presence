package org.example.presenceapp.domain.repo

import org.example.presenceapp.domain.entities.Schedule

interface ScheduleRepository {
    suspend fun getSchedule(group_id: Int): List<Schedule>
}