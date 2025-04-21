package org.example.presenceapp.di

import de.jensklingenberg.ktorfit.Ktorfit
import org.example.presenceapp.data.remote.api.AuthApi
import org.example.presenceapp.data.remote.api.GroupApi
import org.example.presenceapp.data.remote.api.createAuthApi
import org.example.presenceapp.data.remote.impl.AuthApiImpl
import org.example.presenceapp.data.remote.impl.ScheduleApiImpl
import org.example.presenceapp.data.remote.network.KtorfitClient
import org.example.presenceapp.data.repository.AuthRepository
import org.example.presenceapp.data.repository.ScheduleRepository
import org.example.presenceapp.domain.repo.LoginRepository
import org.example.presenceapp.domain.usecases.LoginUseCase
import org.example.project.ui.login.LoginViewModel
import org.koin.dsl.module

val networkModule = module {
    single { KtorfitClient.instance }
    single<AuthApi> { get<Ktorfit>().createAuthApi() }
    single<GroupApi> { get<Ktorfit>().create() }

    single { AuthApiImpl(get()) }
    single { AuthRepository(get(), get()) }

    single { ScheduleApiImpl(get()) }
    single { ScheduleRepository (get()) }

    factory { LoginViewModel(get(), get()) }
}