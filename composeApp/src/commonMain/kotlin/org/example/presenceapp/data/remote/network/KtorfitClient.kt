package org.example.presenceapp.data.remote.network

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.presenceapp.data.remote.api.AuthApi
import org.example.presenceapp.data.remote.api.GroupApi

object KtorfitClient {
    private const val BASE_URL = "http://185.207.0.137:8080/"
    private val json = Json { ignoreUnknownKeys = true }

    private val ktorClient = HttpClient {
        install(ContentNegotiation) {
            json(json)
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    val instance: Ktorfit = Ktorfit.Builder()
        .baseUrl(BASE_URL)
        .httpClient(ktorClient)
        .build()


    fun createAuthApi(): AuthApi {
        return instance.create<AuthApi>()
    }

    fun createScheduleApi(): GroupApi {
        return instance.create<GroupApi>()
    }
}