package com.mafiarosa

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
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

    post("/contact") {
        val form = call.receiveParameters().toMap().mapValues { (_, v) -> v.first() }
        val formPath = "/#contato&form=${Json.encodeToString(form)}"

        call.respondRedirect(
             when {
                !Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$").matches(form["email"]!!) ->
                    "$formPath?msg=E-mail inválido."
                !listOf("name", "email", "subject", "message").all { it in form.keys } ->
                    "$formPath?msg=Preencha todos os campos"
                else -> "/#contato&msg=Formulário Enviado!"
            }
        )
    }
}
