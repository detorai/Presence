package org.example.presenceapp.data.local.storage.attendance

import org.example.presenceapp.PlatformContext

expect class AttendanceStorageProvider(context: PlatformContext) {
    fun provide(): AttendanceStorage
}