package org.example.presenceapp.data.local.storage.attendance

import org.example.presenceapp.PlatformContext

actual class AttendanceStorageProvider actual constructor(private val context: PlatformContext) {
    actual fun provide(): AttendanceStorage {
        return AttendanceStorageAndroid(context.context)
    }
}