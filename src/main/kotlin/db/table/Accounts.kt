package com.mafiarosa.db.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.date

object Accounts : UUIDTable() {
    val name = varchar("name", 2500)
    val birthday = date("birthday")
    val email = varchar("email", 254).uniqueIndex()
    val passwordHash = text("password_hash")

    val address = text("address").nullable().default(null)
    val phone = varchar("phone", 15).nullable().default(null).uniqueIndex()
    val cpf = varchar("cpf", 11).nullable().default(null).uniqueIndex()

    val isAdmin = bool("admin").default(false)
}