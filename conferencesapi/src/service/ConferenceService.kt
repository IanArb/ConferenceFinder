package com.ianarbuckle.conferencesapi.service

import com.ianarbuckle.conferencesapi.models.Conference
import com.ianarbuckle.conferencesapi.repository.ConferenceRepository
import org.litote.kmongo.coroutine.CoroutineClient

class ConferenceService(private val repository: ConferenceRepository) {

    suspend fun findAll(coroutineClient: CoroutineClient): List<Conference> = repository.findAllConferences(coroutineClient)

    suspend fun findOne(id: String, coroutineClient: CoroutineClient): Conference? = repository.findOneConference(id, coroutineClient)

    suspend fun insertEntity(request: Conference, coroutineClient: CoroutineClient) {
        repository.insertConference(request, coroutineClient)
    }

    suspend fun deleteEntity(id: String, coroutineClient: CoroutineClient) {
        repository.deleteConference(id, coroutineClient)
    }

    suspend fun updateEntity(requestBody: Conference?, coroutineClient: CoroutineClient) {
        repository.updateConference(requestBody, coroutineClient)
    }

}