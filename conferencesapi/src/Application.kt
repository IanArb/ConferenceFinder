package com.ianarbuckle.conferencesapi

import com.fasterxml.jackson.databind.SerializationFeature
import com.ianarbuckle.conferencesapi.controller.conferenceRoutes
import com.ianarbuckle.conferencesapi.di.appModule
import com.ianarbuckle.conferencesapi.service.ConferenceService
import io.ktor.application.*
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpMethod
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.core.parameter.parametersOf
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.litote.kmongo.coroutine.CoroutineClient

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start(wait = true)
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        anyHost() //TODO Don't do this in production
    }

    install(Koin) {
        modules(appModule)
    }

    val service: ConferenceService by inject()

    val uri = environment.config.property("ktor.mongoUri").getString()

    val coroutineClient: CoroutineClient by inject {
        parametersOf(uri)
    }

    routing {
        conferenceRoutes(service, coroutineClient)
    }

}

