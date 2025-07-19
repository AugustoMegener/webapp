package com.mafiarosa.db.table

import org.jetbrains.exposed.dao.id.UUIDTable

object ProductImages : UUIDTable() {
    val image = reference("image", Images)
    val product = reference("product", Products)
}