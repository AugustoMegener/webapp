package com.mafiarosa.db.entity

import com.mafiarosa.db.table.ContactForms
import com.mafiarosa.db.table.NewsletterEmails
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class NewsletterEmail(id: EntityID<UUID>) : UUIDEntity(id)  {

    var email by NewsletterEmails.email
    var available by NewsletterEmails.available

    companion object : UUIDEntityClass<NewsletterEmail>(NewsletterEmails)
}