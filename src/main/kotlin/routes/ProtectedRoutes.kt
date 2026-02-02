package com.example.routes

import io.ktor.server.routing.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.response.*

fun Route.protectedRoutes() {
    authenticate("auth-jwt") {
        get("/welcome") {
            //val principal = call.principal<UserIdPrincipal>()
            //call.respondText("Welcome, ${principal?.name}! You are authenticated.")

            val principal = call.principal<JWTPrincipal>()
            val email = principal?.payload?.getClaim("email")?.asString()
            val expiresAt = principal?.expiresAt?.time?.minus(System.currentTimeMillis())
            call.respondText("Hello, $email! Token is expired at $expiresAt ms.")
        }
    }
}
