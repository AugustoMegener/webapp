package com.mafiarosa.routing

import com.mafiarosa.core.ContactSubject
import com.mafiarosa.db.entity.ContactForm
import com.mafiarosa.db.entity.NewsletterEmail
import com.mafiarosa.db.table.NewsletterEmails
import com.mafiarosa.dto.IndexDTO
import io.ktor.server.freemarker.FreeMarkerContent
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext
import io.ktor.util.toLowerCasePreservingASCIIRules
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.text.isBlank

suspend fun RoutingContext.indexGet() { call.respond(FreeMarkerContent("index.ftl", IndexDTO())) }

suspend fun RoutingContext.indexPost() {
    val params = call.receiveParameters()

    call.respond(FreeMarkerContent("index.ftl", when (call.queryParameters["form"]) {
        "newsletter" -> {
            val email = params["email"]?.toLowerCasePreservingASCIIRules() ?: ""

            if (emailRegex.matches(email))
                try {
                    transaction {
                        if(NewsletterEmail.find { NewsletterEmails.email eq email }.empty()) {
                            NewsletterEmail.new { this.email = email}
                            IndexDTO(newsletterInfo = "E-mail inscrito com sucesso!")
                        }
                        else IndexDTO(newsletterInfo = "E-mail já está inscrito.")
                    }
                }
                catch (e: ExposedSQLException) {
                    IndexDTO(
                        newsletterEmail = email,
                        newsletterInfo = "Erro interno, por favor contacte o suporte.\n${e.message}"
                    )
                }
            else IndexDTO(newsletterEmail = email, newsletterInfo = "E-mail inválido.")

        }
        "contact" -> {
            val name    = params["name"]    ?: ""
            val email   = params["email"]   ?: ""
            val subject = ContactSubject.entries.firstOrNull { it.name.equals(params["subject"], true)  }
            val message = params["message"] ?: ""

            val error = { msg: String ->
                IndexDTO(contactName = name, contactEmail = email, contactMessage = message, contactInfo = msg)
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
                        IndexDTO(contactInfo = "Formulário enviado!")
                    }
                    catch (e: ExposedSQLException) {
                        error("Erro interno, por favor contacte o suporte.\n${e.message}")
                    }
            }
        }
        else -> IndexDTO()
    }))
}