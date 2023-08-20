package com.example.service

import com.example.db.DatabaseFactory
import com.example.models.CreateNewUser
import com.example.models.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserServiceImpl : UserServiceInterface {
    override suspend fun CreateNewUser(user: CreateNewUser): User? {
        var query : InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            query = com.example.db.User.insert {
                it[email] = user.email
                it[password] = user.password
                it[fullName] = user.name
                it[avatar] = user.avatar
            }
        }
        return rawToUser(query?.resultedValues?.get(0))
    }

    override suspend fun FindExistUser(email: String): User? {
        val user = DatabaseFactory.dbQuery {
            com.example.db.User.select { com.example.db.User.email.eq(email) }.map { rawToUser(it) }.singleOrNull()
        }
        return user
    }

    private fun rawToUser(get: ResultRow?) : User?{
        return if (get == null) null
        else User(
            id = get[com.example.db.User.id],
            fullName = get[com.example.db.User.fullName],
            avatar = get[com.example.db.User.avatar],
            email = get[com.example.db.User.email],
            password = get[com.example.db.User.password],
            createdAt = get[com.example.db.User.createdAt].toString()
        )
    }
}