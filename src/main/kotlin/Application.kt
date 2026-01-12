package com.example

import com.example.state.JwtConfig
import com.example.state.UserStore
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Authentication) {
        jwt("auth-jwt") {
            realm = "ktor-auth"
            verifier(JwtConfig.verifier)

            // Allow raw token with or without "Bearer" prefix
            // not really working when token with Bearer prefix
            // this could throw 500 internal server error
            // io.ktor.http.parsing.ParseException: Invalid blob value: it should be token68
//            authHeader { call ->
//                val authHeaderStr = call.request.headers["Authorization"]
//                when {
//                    authHeaderStr == null -> null // No Authorization header
//                    authHeaderStr.startsWith("Bearer ") -> {
//                        val tokenStr = authHeaderStr.removePrefix("Bearer ").trim()
//                        println("==> tokenStr=$tokenStr")
//                        HttpAuthHeader.Single("Bearer ", tokenStr)
//                    }
//                    else -> HttpAuthHeader.Single("Bearer", authHeaderStr.trim()) // Accept raw token
//                }
//            }

            validate { credential ->
                val email = credential.payload.getClaim("email").asString()
                if (UserStore.findUser(email) != null) {
                    UserIdPrincipal(email)
                } else null
            }
        }
    }

    install(ContentNegotiation) {
        json(Json{
            prettyPrint = true // set to false to reduce bandwidth in production
            isLenient = false
        })
    }

    configureHTTP()
    configureRouting()
}
