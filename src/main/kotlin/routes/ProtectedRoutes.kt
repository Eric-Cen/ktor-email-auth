package com.example.routes

import io.ktor.server.routing.*
import io.ktor.server.auth.*
import io.ktor.server.response.*

fun Route.protectedRoutes() {
    authenticate("auth-jwt") {
        get("/welcome") {
            val principal = call.principal<UserIdPrincipal>()
            call.respondText("Welcome, ${principal?.name}! You are authenticated.")
        }
    }
}
