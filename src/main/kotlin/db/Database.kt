package com.mafiarosa.db

import com.mafiarosa.db.table.ContactForms
import com.mafiarosa.db.table.Images
import com.mafiarosa.db.table.NewsletterEmails
import com.mafiarosa.db.table.ProductImages
import com.mafiarosa.db.table.Products
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun initDatabase() {
    Database.connect(
        url = "jdbc:mysql://localhost:3306/mafiarosa",
        driver = "com.mysql.cj.jdbc.Driver",
        user = System.getenv("DB_USER"),
        password = System.getenv("DB_PASSWORD")
    )

    transaction {
        SchemaUtils.create(ContactForms, Images, NewsletterEmails, ProductImages, Products)
    }
}