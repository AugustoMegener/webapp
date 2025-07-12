package com.mafiarosa.db.table

import com.mafiarosa.core.ContactSubject
import org.jetbrains.exposed.dao.id.UUIDTable

object ContactForms : UUIDTable() {
    val name = varchar("name", 150)
    val email = varchar("email", 150)
    val subject = enumerationByName<ContactSubject>("subject", ContactSubject.entries.size)
    val message = text("message")
}