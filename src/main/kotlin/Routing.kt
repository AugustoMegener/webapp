package com.mafiarosa

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.defaultResource
import io.ktor.server.http.content.resources
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.request.receiveParameters
import io.ktor.server.request.receiveStream
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import io.ktor.util.toMap
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

@OptIn(ExperimentalSerializationApi::class)
fun Routing.configureRouting() {

    install(ContentNegotiation) { json() }


    staticResources("/", "static")

    get("/") {
        call.respond(FreeMarkerContent("index.ftl", when (call.request.queryParameters["form"]) {

            else -> mapOf<String, String>()
        }))
    }
}
