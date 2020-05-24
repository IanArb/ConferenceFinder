package com.ianarbuckle.conferencesapi.service

import com.ianarbuckle.conferencesapi.Constants
import com.ianarbuckle.conferencesapi.models.Conference
import com.ianarbuckle.conferencesapi.mongo.MongoBuilder
import com.mongodb.client.model.InsertOneOptions

class ConferenceService {

    suspend fun findAll(): List<Conference> {
        return MongoBuilder.mongoCoroutineClient().getDatabase(Constants.DATABASE_NAME)
            .getCollection<Conference>(Constants.COLLECTION_NAME)
            .find()
            .toList()
    }

    suspend fun insertEntity(request: Conference) {
        MongoBuilder.mongoCoroutineClient().getDatabase(Constants.DATABASE_NAME)
            .getCollection<Conference>(Constants.COLLECTION_NAME)
            .insertOne(request, InsertOneOptions())
    }
}