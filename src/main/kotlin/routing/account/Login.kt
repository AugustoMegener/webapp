package com.mafiarosa.routing.account

import com.mafiarosa.core.ContactSubject
import com.mafiarosa.db.entity.Account
import com.mafiarosa.db.entity.ContactForm
import com.mafiarosa.db.entity.NewsletterEmail
import com.mafiarosa.db.entity.Product
import com.mafiarosa.db.table.Accounts
import com.mafiarosa.db.table.NewsletterEmails
import com.mafiarosa.dto.IndexDTO
import com.mafiarosa.dto.LoginDTO
import com.mafiarosa.routing.emailRegex
import com.mafiarosa.session.AccountSession
import io.ktor.server.freemarker.FreeMarkerContent
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.RoutingContext
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import io.ktor.util.toLowerCasePreservingASCIIRules
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID
import kotlin.text.isBlank

suspend fun RoutingContext.loginGet() {
    call.respond(FreeMarkerContent("account/login.ftl", LoginDTO()))
}

suspend fun RoutingContext.indexPost() {
    val params = call.receiveParameters()

    when (call.queryParameters["form"]) {
        "login" -> {
            val email = params["email"]
            val password = params["password"]

            if (email == null || password == null)
                call.respond(FreeMarkerContent("account/login.ftl", LoginDTO(loginInfo = "Insira o email e a senha.")))
            else {
                val account = transaction { Account.find { Accounts.email eq email }.firstOrNull() }

                if (account == null)
                    call.respond(
                        FreeMarkerContent("account/login.ftl", LoginDTO(loginInfo = "Não há conta com esse email."))
                    )
                else {
                    if (account.checkPassword(password)) {
                        call.sessions.set(AccountSession(account.id.value.toString()))
                        call.respondRedirect("/account")
                    } else call.respond(
                        FreeMarkerContent("account/login.ftl", LoginDTO(loginInfo = "Senha incorreta."))
                    )
                }
            }
        }
        "signin" -> {

        }
        else -> call.respond(FreeMarkerContent("account/login.ftl", LoginDTO()))
    }
}