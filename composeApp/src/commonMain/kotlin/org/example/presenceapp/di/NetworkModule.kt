package org.example.presenceapp.di

import cafe.adriel.voyager.navigator.Navigator
import de.jensklingenberg.ktorfit.Ktorfit
import org.example.presenceapp.data.common.dto.presence.PresenceNetRepository
import org.example.presenceapp.data.remote.api.AuthApi
import org.example.presenceapp.data.remote.api.GroupApi
import org.example.presenceapp.data.remote.api.PresenceApi
import org.example.presenceapp.data.remote.impl.AuthApiImpl
import org.example.presenceapp.data.remote.impl.GroupApiImpl
import org.example.presenceapp.data.remote.impl.PresenceApiImpl
import org.example.presenceapp.data.remote.network.KtorfitClient
import org.example.presenceapp.data.repository.AuthRepository
import org.example.presenceapp.data.repository.GroupNetRepository
import org.example.presenceapp.domain.repo.LoginRepository
import org.example.presenceapp.domain.repo.GroupRepository
import org.example.presenceapp.domain.repo.PresenceRepository
import org.example.presenceapp.domain.usecases.LoginUseCase
import org.example.presenceapp.domain.usecases.ScheduleUseCase
import org.example.presenceapp.ui.feature.info.InfoScreenModel
import org.example.presenceapp.ui.feature.schedule.ScheduleScreenModel
import org.example.presenceapp.ui.navigation.AppNavigator
import org.example.presenceapp.ui.navigation.GlobalNavigator
import org.example.project.ui.login.LoginViewModel
import org.koin.dsl.module

val networkModule = module {
    single { KtorfitClient.instance }
    single<AuthApi> { get<Ktorfit>().create() }
    single<GroupApi> { get<Ktorfit>().create() }
    single<PresenceApi> { get<Ktorfit>().create() }

    single { AuthApiImpl(get()) }
    single<LoginRepository> { AuthRepository(get(), get(), get()) }

    single { GroupApiImpl(get()) }
    single<GroupRepository> { GroupNetRepository (get(), get()) }

    single { PresenceApiImpl(get()) }
    single <PresenceRepository> { PresenceNetRepository(get()) }

    single { LoginUseCase(get()) }
    single { ScheduleUseCase(get()) }

    factory { InfoScreenModel(get()) }
    factory { parameters ->
        ScheduleScreenModel(get(), parameters.get())
    }
    factory { LoginViewModel(get(), get()) }
}