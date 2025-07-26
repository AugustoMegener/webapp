package com.mafiarosa.routing

import com.mafiarosa.core.ContactSubject
import com.mafiarosa.db.entity.ContactForm
import com.mafiarosa.db.entity.NewsletterEmail
import com.mafiarosa.db.table.NewsletterEmails
import com.mafiarosa.dto.IndexDTO
import com.mafiarosa.routing.account.loginGet
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.toLowerCasePreservingASCIIRules
import kotlinx.serialization.ExperimentalSerializationApi
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.transactions.transaction

val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

@OptIn(ExperimentalSerializationApi::class)
fun Routing.configureRouting() {

    install(ContentNegotiation) { json() }

    staticResources("/", "static")

    get("/") { indexGet() };              post("/") { indexPost() }
    get("/account/login") { loginGet() }; post("/account/login") { indexPost() }
}
