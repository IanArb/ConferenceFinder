package com.ianarbuckle.conferencesfinder.ui.conferences.usecase

import conferences.model.Conference
import conferences.repository.ConferencesRepository
import conferences.utils.Result
import javax.inject.Inject

class ConferencesUseCase @Inject constructor(private val repository: ConferencesRepository) {

    suspend fun conferences(): Result<List<Conference>> = repository.fetchConferences()
}