package com.mafiarosa.db.entity

import com.mafiarosa.core.ContactSubject
import com.mafiarosa.db.table.ContactForms
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class ContactForm(id: EntityID<UUID>) : UUIDEntity(id) {

    var name    by ContactForms.name
    var email   by ContactForms.email
    var subject by ContactForms.subject
    var message by ContactForms.message
    var status  by ContactForms.status

    companion object : UUIDEntityClass<ContactForm>(ContactForms)
}