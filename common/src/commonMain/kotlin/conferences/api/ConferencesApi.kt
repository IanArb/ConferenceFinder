package conferences.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import conferences.model.Conference
import io.ktor.http.*

class ConferencesApi {

    private val baseUrl = "https://conferences-web-api.herokuapp.com"

    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

    private val client by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(nonStrictJson)
            }
        }
    }

    suspend fun fetchConferences() = client.get<List<Conference>>("$baseUrl/conferences")

    suspend fun fetchConferenceById(id: String) = client.get<Conference>("$baseUrl/conferences/${id}")
}