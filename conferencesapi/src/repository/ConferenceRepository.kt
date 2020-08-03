package com.ianarbuckle.conferencesapi.repository

import com.ianarbuckle.conferencesapi.Constants
import com.ianarbuckle.conferencesapi.models.Conference
import com.mongodb.client.model.InsertOneOptions
import org.litote.kmongo.coroutine.CoroutineClient

class ConferenceRepository {

    suspend fun insertConference(request: Conference, coroutineClient: CoroutineClient) {
        coroutineCollection(coroutineClient)
            .insertOne(request)
    }

    suspend fun findAllConferences(coroutineClient: CoroutineClient): List<Conference> =
        coroutineCollection(coroutineClient)
            .find()
            .toList()

    suspend fun findOneConference(id: String, coroutineClient: CoroutineClient): Conference? =
        coroutineCollection(coroutineClient)
            .findOneById(id)

    suspend fun deleteConference(id: String, coroutineClient: CoroutineClient) {
        coroutineCollection(coroutineClient)
            .deleteOneById(id)
    }

    suspend fun updateConference(requestBody: Conference?, coroutineClient: CoroutineClient) {
        requestBody?.let {
            coroutineCollection(coroutineClient)
                .updateOneById(it._id!!, it)
        }
    }

    private fun coroutineCollection(coroutineClient: CoroutineClient) =
        coroutineClient.getDatabase(Constants.DATABASE_NAME)
            .getCollection<Conference>(Constants.COLLECTION_NAME_V2)

}