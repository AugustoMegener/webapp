package com.mafiarosa.db

import org.jetbrains.exposed.sql.Database

fun initDatabase() {
    Database.connect(
        url = "jdbc:mysql://localhost:3306/mafiarosa",
        driver = "com.mysql.cj.jdbc.Driver",
        user = System.getenv("DB_USER"),
        password = System.getenv("DB_PASSWORD")
    )
}