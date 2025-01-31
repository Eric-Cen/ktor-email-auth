package com.example

import com.example.model.AuthRequest
import com.example.state.UserStore
import com.example.utils.isValidEmail
import com.example.utils.isValidPassword
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.route
import io.ktor.server.routing.post
import io.ktor.http.HttpStatusCode

fun Route.authRoutes() {

    route("/auth") {
        post("/signup") {
            try {
                val request = call.receive<AuthRequest>()
                if (!request.email.isValidEmail()) {
                    throw IllegalArgumentException("Invalid email format")
                }
                if (!request.password.isValidPassword()) {
                    throw IllegalArgumentException(
                        "Password must be at least 12 characters long and include at least one uppercase letter, one lowercase letter, and one special character."
                    )
                }
                if (UserStore.addUser(request.email, request.password)) {
                    call.respond(HttpStatusCode.Created, "User registered successfully")
                } else {
                    call.respond(HttpStatusCode.Conflict, "User already exists")
                }
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid request data")
            }
        }

        post("/login") {
            try {
                val request = call.receive<AuthRequest>()
                if (UserStore.validateUser(request.email, request.password)) {
                    call.respond(HttpStatusCode.OK, "Login successful")
                } else {
                    call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
                }
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid request data")
            }
        }
    }
}
