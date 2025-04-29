package org.example.presenceapp.data.common.dto.presence

import org.example.presenceapp.data.common.toDto
import org.example.presenceapp.data.common.toEntity
import org.example.presenceapp.data.remote.impl.PresenceApiImpl
import org.example.presenceapp.domain.command.GroupCommand
import org.example.presenceapp.domain.command.PresenceCommand
import org.example.presenceapp.domain.entities.AttendanceTypeNet
import org.example.presenceapp.domain.entities.Presence
import org.example.presenceapp.domain.entities.Presetting
import org.example.presenceapp.domain.repo.PresenceRepository

class PresenceNetRepository(
    private val presenceApiImpl: PresenceApiImpl
): PresenceRepository {
    override suspend fun getAttendanceType(): List<AttendanceTypeNet> {
        val result = presenceApiImpl.getAttendanceType()
        return result.map { it.toEntity() }
    }

    override suspend fun getPresetting(groupCommand: GroupCommand): List<Presetting> {
        val result = presenceApiImpl.getPresetting(groupCommand.toDto())
        return result.map { it.toEntity() }
    }

    override suspend fun sendPresence(presenceCommand: List<PresenceCommand>): List<Presence> {
        val result = presenceApiImpl.sendPresence(presenceCommand.map { it.toDto() })
        return result.map { it.toEntity() }
    }
}