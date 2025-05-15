package org.example.presenceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.presenceapp.di.androidModule
import org.example.presenceapp.di.navigationModule
import org.example.presenceapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.compose.getKoin
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            logger(PrintLogger(Level.DEBUG))
            androidContext(applicationContext)
            modules(listOf(networkModule, navigationModule(navigator), androidModule))
        }

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}