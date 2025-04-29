package org.example.presenceapp.domain.repo

import org.example.presenceapp.data.common.dto.presence.PresettingDto
import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.command.PresenceCommand
import org.example.presenceapp.domain.entities.AttendanceTypeNet
import org.example.presenceapp.domain.entities.Presence
import org.example.presenceapp.domain.entities.Presetting

interface PresenceRepository {
    suspend fun getAttendanceType(): List<AttendanceTypeNet>
    suspend fun getPresetting(groupCommand: GroupCommand): List<Presetting>
    suspend fun sendPresence(presenceCommand: List<PresenceCommand>): List<Presence>
}