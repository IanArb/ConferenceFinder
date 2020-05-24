package com.ianarbuckle.conferencesapi

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.ianarbuckle.conferencesapi.controller.conferenceRoutes
import io.ktor.application.*
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpMethod
import io.ktor.jackson.jackson
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        jackson {
            registerModule(KotlinModule())
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
    }

    routing {
        conferenceRoutes()
    }

}

