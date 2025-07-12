package com.mafiarosa.db.entity

import com.mafiarosa.core.ContactSubject
import com.mafiarosa.db.table.ContactForms
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class ContactForm(id: EntityID<UUID>) : UUIDEntity(id) {

    val name by ContactForms.name
    val email by ContactForms.email
    val subject by ContactForms.subject
    val message by ContactForms.message

    companion object : UUIDEntityClass<ContactForm>(ContactForms)
}