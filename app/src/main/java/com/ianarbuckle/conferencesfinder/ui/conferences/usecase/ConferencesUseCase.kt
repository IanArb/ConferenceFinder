package com.ianarbuckle.conferencesfinder.ui.conferences.usecase

import conferences.model.Conference
import conferences.repository.ConferencesRepository
import conferences.utils.Result
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConferencesUseCase @Inject constructor(private val repository: ConferencesRepository) {

    fun conferences(): Flow<Result<List<Conference>>> = flow {
        emit(repository.fetchConferences())
    }

    suspend fun fetchConferenceById(id: String) = repository.fetchConferenceById(id)
}