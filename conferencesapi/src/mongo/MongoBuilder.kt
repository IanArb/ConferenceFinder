package com.ianarbuckle.conferencesapi.mongo

import com.ianarbuckle.conferencesapi.Constants
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

object MongoBuilder {

    fun mongoCoroutineClient(): CoroutineClient = KMongo.createClient(Constants.MONGO_URI).coroutine
}