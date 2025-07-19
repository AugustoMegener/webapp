package com.mafiarosa.db.table

import com.mafiarosa.core.ContactStatus
import com.mafiarosa.core.ContactSubject
import org.jetbrains.exposed.dao.id.UUIDTable

object ContactForms : UUIDTable() {
    val name = varchar("name", 150)
    val email = varchar("email", 150)
    val subject = enumeration<ContactSubject>("subject")
    val message = text("message")
    val status = enumeration<ContactStatus>("status").default(ContactStatus.UNREAD)
}