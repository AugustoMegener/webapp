package com.mafiarosa.routing.admin

import com.mafiarosa.core.ContactSubject
import com.mafiarosa.db.entity.ContactForm
import com.mafiarosa.db.entity.NewsletterEmail
import com.mafiarosa.db.table.NewsletterEmails
import com.mafiarosa.dto.IndexDTO
import com.mafiarosa.routing.emailRegex
import io.ktor.server.freemarker.FreeMarkerContent
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingContext
import io.ktor.util.toLowerCasePreservingASCIIRules
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.text.isBlank

suspend fun RoutingContext.adminGet() { }