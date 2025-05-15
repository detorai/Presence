package org.example.presenceapp.di

import cafe.adriel.voyager.navigator.Navigator
import org.example.presenceapp.ui.navigation.AppNavigator
import org.example.presenceapp.ui.navigation.GlobalNavigator
import org.koin.core.module.Module
import org.koin.dsl.module

fun navigationModule(navigator: Navigator): Module = module {
    single<AppNavigator> { GlobalNavigator(navigator) }
}