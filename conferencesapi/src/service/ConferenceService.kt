package com.ianarbuckle.conferencesapi.service

import com.ianarbuckle.conferencesapi.models.Conference
import com.ianarbuckle.conferencesapi.repository.ConferenceRepository
import org.litote.kmongo.coroutine.CoroutineClient

class ConferenceService(private val repository: ConferenceRepository) {

    suspend fun findAll(coroutineClient: CoroutineClient): List<Conference> = repository.findAllConferences(coroutineClient)

    suspend fun insertEntity(request: Conference, coroutineClient: CoroutineClient) {
        repository.insertConference(request, coroutineClient)
    }

}