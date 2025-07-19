package com.mafiarosa.db.entity

import com.mafiarosa.db.table.Images
import com.mafiarosa.db.table.ProductImages
import com.mafiarosa.db.table.Products
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

class Product(id: EntityID<UUID>) : UUIDEntity(id) {

    val name by Products.name
    val price by Products.price
    val description by Products.description
    val images by Image via ProductImages

    companion object : UUIDEntityClass<Product>(Products)
}