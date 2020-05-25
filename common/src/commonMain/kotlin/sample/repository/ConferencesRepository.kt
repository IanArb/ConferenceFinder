package sample.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import sample.api.ConferencesApi
import sample.model.Conference

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