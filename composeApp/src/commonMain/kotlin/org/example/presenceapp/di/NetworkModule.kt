package org.example.presenceapp.di

import de.jensklingenberg.ktorfit.Ktorfit
import org.example.presenceapp.data.local.sql.cache.Database
import org.example.presenceapp.data.local.sql.cache.DatabaseDriverFactory
import org.example.presenceapp.data.remote.api.AuthApi
import org.example.presenceapp.data.remote.api.GroupApi
import org.example.presenceapp.data.remote.api.createAuthApi
import org.example.presenceapp.data.remote.api.createGroupApi
import org.example.presenceapp.data.remote.impl.AuthApiImpl
import org.example.presenceapp.data.remote.impl.ScheduleApiImpl
import org.example.presenceapp.data.remote.network.KtorfitClient
import org.example.presenceapp.data.repository.AuthRepository
import org.example.presenceapp.data.repository.ScheduleNetRepository
import org.example.presenceapp.domain.repo.LoginRepository
import org.example.presenceapp.domain.repo.ScheduleRepository
import org.example.presenceapp.domain.usecases.LoginUseCase
import org.example.presenceapp.ui.info.InfoScreenModel
import org.example.project.ui.login.LoginViewModel
import org.koin.dsl.module

val networkModule = module {
    single { KtorfitClient.instance }
    single<AuthApi> { get<Ktorfit>().createAuthApi() }
    single<GroupApi> { get<Ktorfit>().createGroupApi() }

    single { AuthApiImpl(get()) }
    single<LoginRepository> { AuthRepository(get(), get(), get()) }

    single { LoginUseCase(get()) }

    single { ScheduleApiImpl(get()) }
    single<ScheduleRepository> { ScheduleNetRepository (get()) }


    factory { InfoScreenModel(get()) }
    factory { LoginViewModel(get(), get()) }
}