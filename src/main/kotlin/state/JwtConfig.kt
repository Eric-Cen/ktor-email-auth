package com.example.state

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

object JwtConfig {
    private const val SECRET = "your_secret_key" //TODO Store securely in environment variables
    private const val ISSUER = "ktor.io"
    private const val AUDIENCE = "ktor-users"
    private const val EXPIRATION_TIME = 36_000_00 * 24 // 24 hours

    private val algorithm = Algorithm.HMAC256(SECRET)

    val verifier = JWT.require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    fun generateToken(email: String): String {
        return JWT.create()
            .withIssuer(ISSUER)
            .withAudience(AUDIENCE)
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(algorithm)
    }
}
