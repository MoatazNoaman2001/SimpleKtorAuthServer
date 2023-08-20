package com.example.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversions.string
import java.time.LocalDateTime

object User : Table("Users2") {

    val id = integer("id").autoIncrement()
    val fullName = varchar("full_name", 256)
    val avatar = text("avatar")
    val email = varchar("email", 256)
    val password = text("password")
    val createdAt = datetime("createdAt").clientDefault { LocalDateTime.now() }
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}