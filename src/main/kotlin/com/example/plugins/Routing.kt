package com.example.plugins

import com.example.models.CreateNewUser
import com.example.repository.UserRepo
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(repo: UserRepo) {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        route("/auth") {
            post("/user") {
                val user = call.receive<CreateNewUser>()
                val res = repo.registerUser(user)
                call.respond(res.statusCode , res)
            }
        }
    }
}
