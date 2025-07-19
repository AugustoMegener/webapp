package com.mafiarosa.db.table

import org.jetbrains.exposed.dao.id.UUIDTable

object Products : UUIDTable() {
    val name = varchar("name", 500)
    val price = float("price")
    val description = text("description")
    
}