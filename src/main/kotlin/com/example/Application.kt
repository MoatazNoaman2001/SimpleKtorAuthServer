package com.example

import com.example.db.DatabaseFactory
import com.example.plugins.*
import com.example.repository.UserRepo
import com.example.repository.UserRepoImpl
import com.example.service.UserServiceImpl
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    install(ContentNegotiation){
        jackson()
    }

    val repo = UserRepoImpl(UserServiceImpl())

//    configureSerialization()
//    configureDatabases()
    configureRouting(repo)
}
