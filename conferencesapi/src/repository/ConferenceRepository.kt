package com.ianarbuckle.conferencesapi.repository

import com.ianarbuckle.conferencesapi.Constants
import com.ianarbuckle.conferencesapi.models.Conference
import com.mongodb.client.model.InsertOneOptions
import org.litote.kmongo.coroutine.CoroutineClient

class ConferenceRepository {

    suspend fun insertConference(request: Conference, coroutineClient: CoroutineClient) {
        coroutineClient.getDatabase(Constants.DATABASE_NAME)
            .getCollection<Conference>(Constants.COLLECTION_NAME)
            .insertOne(request, InsertOneOptions())
    }

    suspend fun findAllConferences(coroutineClient: CoroutineClient): List<Conference> =
        coroutineClient.getDatabase(Constants.DATABASE_NAME)
            .getCollection<Conference>(Constants.COLLECTION_NAME)
            .find()
            .toList()
}