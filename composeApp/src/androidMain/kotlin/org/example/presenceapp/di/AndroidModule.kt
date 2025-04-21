package org.example.presenceapp.di

import org.example.presenceapp.data.local.LocalDataSource
import org.example.presenceapp.data.local.storage.attendance.AttendanceStorage
import org.example.presenceapp.data.local.storage.attendance.AttendanceStorageAndroid
import org.example.presenceapp.data.local.storage.login.AndroidTokenStorage
import org.example.presenceapp.ui.settings.getSettingsManager
import org.example.project.data.local.AuthManager
import org.example.project.data.local.TokenStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single<TokenStorage> { AndroidTokenStorage(androidContext()) }
    single { AuthManager(get()) }

    single<AttendanceStorage> { AttendanceStorageAndroid(androidContext()) }
    single { LocalDataSource(get()) }
}