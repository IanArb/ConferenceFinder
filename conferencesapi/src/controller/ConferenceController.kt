package com.ianarbuckle.conferencesapi.controller

import com.ianarbuckle.conferencesapi.models.Conference
import com.ianarbuckle.conferencesapi.service.ConferenceService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.conferenceRoutes() {
    val service by lazy { ConferenceService() }

    route("/conferences") {

        get {
            call.respond(HttpStatusCode.OK, service.findAll())
        }

        post<Conference>("") { request ->
            service.insertEntity(request)

            call.respond(HttpStatusCode.Created)
        }
    }
}