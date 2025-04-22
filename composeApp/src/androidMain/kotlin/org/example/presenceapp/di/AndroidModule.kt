package org.example.presenceapp.di

import org.example.presenceapp.data.local.LocalDataSource
import org.example.presenceapp.data.local.sql.cache.AndroidDatabaseDriverFactory
import org.example.presenceapp.data.local.sql.cache.DatabaseDriverFactory
import org.example.presenceapp.data.local.storage.attendance.AttendanceStorage
import org.example.presenceapp.data.local.storage.attendance.AttendanceStorageAndroid
import org.example.presenceapp.data.local.storage.login.AndroidTokenStorage
import org.example.presenceapp.data.local.storage.login.AndroidUserInfoStorage
import org.example.presenceapp.data.local.storage.login.UserInfoStorage
import org.example.presenceapp.data.repository.AuthRepository
import org.example.presenceapp.domain.repo.LoginRepository
import org.example.presenceapp.domain.usecases.LoginUseCase
import org.example.presenceapp.ui.settings.getSettingsManager
import org.example.project.data.local.AuthManager
import org.example.project.data.local.TokenStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single<TokenStorage> { AndroidTokenStorage(androidContext()) }
    single<UserInfoStorage> { AndroidUserInfoStorage(androidContext()) }
    single { AuthManager(get(),get()) }

    single<AttendanceStorage> { AttendanceStorageAndroid(androidContext()) }
    single { LocalDataSource(get()) }

    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(androidContext()) }
}