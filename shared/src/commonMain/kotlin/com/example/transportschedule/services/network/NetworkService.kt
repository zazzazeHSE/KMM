package com.example.transportschedule.services.network

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

class NetworkService {
    val httpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
            acceptContentTypes = acceptContentTypes + ContentType.Any
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 200_000L
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
    }

    suspend inline fun <reified T> loadData(url: String): T? {
        return httpClient.get(url)
    }
}