package com.mafiarosa.db.entity

import com.mafiarosa.db.table.Images
import com.mafiarosa.db.table.NewsletterEmails
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class Image(id: EntityID<UUID>) : UUIDEntity(id) {

    val uri by Images.uri
    val description by Images.description

    companion object : UUIDEntityClass<Image>(Images)
}