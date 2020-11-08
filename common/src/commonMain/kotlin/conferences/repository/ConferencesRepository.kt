package conferences.repository

import conferences.api.ConferencesApi
import conferences.model.Conference
import conferences.utils.Result
import conferences.utils.safeApiCall
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConferencesRepository {

    private val conferencesApi = ConferencesApi()

    private suspend fun conferences(): Result<List<Conference>> {
        val result = conferencesApi.fetchConferences()

        return when {
            !result.isNullOrEmpty() -> Result.Success(result)
            else -> Result.Error(IOException("Error getting conferences"))
        }
    }

    private suspend fun findOneConference(id: String): Result<Conference> {
        val result = conferencesApi.fetchConferenceById(id)

        return when {
            result.id.isNotEmpty() -> Result.Success(result)
            else -> Result.Error(IOException("Error getting conference"))
        }
    }

    suspend fun fetchConferences() = safeApiCall(
        call = { conferences() },
        errorMessage = "Failed to retrieve conferences"
    )

    //iOS
    fun fetchConferences(success: (List<Conference>) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = fetchConferences()
            if (result is Result.Success) {
                success(result.data)
            } else {
                success(emptyList())
            }
        }
    }

    suspend fun fetchConferenceById(id: String) = safeApiCall(
        call = { findOneConference(id) },
        errorMessage = "Failed to get conference"
    )


}