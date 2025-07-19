package com.mafiarosa.db.table

import org.jetbrains.exposed.dao.id.UUIDTable

object Images : UUIDTable() {
    val uri = text("uri")
    val description = text("description")
}