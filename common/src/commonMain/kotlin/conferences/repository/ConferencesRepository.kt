package conferences.repository

import conferences.api.ConferencesApi
import conferences.model.Conference
import conferences.utils.Result
import conferences.utils.safeApiCall
import io.ktor.utils.io.errors.IOException

class ConferencesRepository {

    private val conferencesApi = ConferencesApi()

    suspend fun conferences(): Result<List<Conference>> {
        val result = conferencesApi.fetchConferences()

        return when {
            !result.isNullOrEmpty() -> Result.Success(result)
            else -> Result.Error(IOException("Error getting conferences"))
        }
    }

    suspend fun fetchConferences() = safeApiCall(
        call = { conferences() },
        errorMessage = "Failed to retrieve conferences"
    )

//    //iOS
//    fun fetchConferences(success: (List<Conference>) -> Unit) {
//        GlobalScope.launch(Dispatchers.Main) {
//            success(fetchConferences())
//        }
//    }


}