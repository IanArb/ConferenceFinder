package conferences.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import conferences.model.Conference
import io.ktor.client.request.header

class ConferencesApi {

    private val baseUrl = "https://conferences-web-api.herokuapp.com"

    private val client by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json(JsonConfiguration(ignoreUnknownKeys = true)))
            }
        }
    }

    suspend fun fetchConferences() = client.get<List<Conference>>("$baseUrl/conferences")
}