package com.mafiarosa

import com.mafiarosa.core.ContactSubject
import com.mafiarosa.db.entity.ContactForm
import com.mafiarosa.db.entity.NewsletterEmail
import com.mafiarosa.page.IndexData
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
import io.ktor.util.toUpperCasePreservingASCIIRules
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.transactions.transaction

val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

@OptIn(ExperimentalSerializationApi::class)
fun Routing.configureRouting() {

    install(ContentNegotiation) { json() }


    staticResources("/", "static")

    get("/") { call.respond(FreeMarkerContent("index.ftl", IndexData())) }

    post("/") {
        val params = call.receiveParameters()

        call.respond(FreeMarkerContent("index.ftl", when (call.queryParameters["form"]) {
            "newsletter" -> {
                val email = params["email"] ?: ""

                if (emailRegex.matches(email))
                    try {
                        transaction { NewsletterEmail.new { this.email = email } }
                        IndexData(newsletterInfo = "E-mail inscrito com sucesso!")
                    }
                    catch (e: ExposedSQLException) {
                        IndexData(
                            newsletterEmail = email,
                            newsletterInfo = "Erro interno, por favor contacte o suporte.\n${e.message}"
                        )
                    }
                else IndexData(newsletterEmail = email, newsletterInfo = "E-mail inválido.")

            }
            "contact" -> {
                val name    = params["name"]    ?: ""
                val email   = params["email"]   ?: ""
                val subject = ContactSubject.entries.firstOrNull { it.name.equals(params["subject"], true)  }
                val message = params["message"] ?: ""

                val error = { msg: String ->
                    IndexData(contactName = name, contactEmail = email, contactMessage = message, contactInfo = msg)
                }

                when {
                    name.isBlank() ->
                        error("Nome em branco, por favor preencha.")
                    !emailRegex.matches(email) ->
                        error("Email inválido.")
                    subject == null ->
                        error("Assunto inválido.")
                    message.isBlank() ->
                        error("Mensagem em branco, por favor preencha.")
                    else ->
                        try {
                            transaction { ContactForm.new {
                                this.name = name
                                this.email = email
                                this.subject = subject
                                this.message = message
                            } }
                            IndexData(contactInfo = "Formulário enviado!")
                        }
                        catch (e: ExposedSQLException) {
                            error("Erro interno, por favor contacte o suporte.\n${e.message}")
                        }
                }
            }
            else -> IndexData()
        }))
    }
}
