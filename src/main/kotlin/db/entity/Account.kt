package com.mafiarosa.db.entity

import at.favre.lib.crypto.bcrypt.BCrypt
import com.mafiarosa.db.table.Accounts
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class Account(id: EntityID<UUID>) : UUIDEntity(id) {
    var name by Accounts.name
    var birthday by Accounts.birthday
    var mail by Accounts.email
    private var passwordHash by Accounts.passwordHash

    var address by Accounts.address
    var phone by Accounts.phone
    var cpf by Accounts.cpf

    var isAdmin by Accounts.isAdmin

    var password: String
        get() = error("Can't get a set only var")
        set(value) { passwordHash = BCrypt.withDefaults().hashToString(12, value.toCharArray()) }

    fun checkPassword(hash: String): Boolean =
        BCrypt.verifyer().verify(hash.toCharArray(), passwordHash).verified

    companion object : UUIDEntityClass<Account>(Accounts)
}
