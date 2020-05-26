package conferences.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import conferences.api.ConferencesApi
import conferences.model.Conference

class ConferencesRepository {

    private val conferencesApi = ConferencesApi()

    suspend fun fetchConferences(): List<Conference> = conferencesApi.fetchConferences()

    //iOS
    fun fetchConferences(success: (List<Conference>) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            success(fetchConferences())
        }
    }


}