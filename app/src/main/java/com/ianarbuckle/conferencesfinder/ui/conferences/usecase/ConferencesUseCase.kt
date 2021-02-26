package com.ianarbuckle.conferencesfinder.ui.conferences.usecase

import conferences.model.Conference
import conferences.repository.ConferencesRepository
import conferences.utils.Result
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class ConferencesUseCase @Inject constructor(private val repository: ConferencesRepository) {

    suspend fun conferences(): Result<List<Conference>> = repository.fetchConferences()

    suspend fun fetchConferenceById(id: String) = repository.fetchConferenceById(id)
}