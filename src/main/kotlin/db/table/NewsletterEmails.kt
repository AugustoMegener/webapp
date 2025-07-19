package com.mafiarosa.db.table

import org.jetbrains.exposed.dao.id.UUIDTable

object NewsletterEmails : UUIDTable() {
    val email = varchar("email", 254)
    val available = bool("available").default(true)
}