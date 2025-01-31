package com.example.state

import com.example.model.User

object UserStore {

    private val users = mutableMapOf<String, User>()

    fun addUser(email: String, password: String): Boolean {
        if (users.containsKey(email)) return false // User already exists

        val hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt())
        users[email] = User(email, hashedPassword)
        return true
    }

    fun validateUser(email: String, password: String): Boolean {
        val user = users[email] ?: return false
        return org.mindrot.jbcrypt.BCrypt.checkpw(password, user.hashedPassword)
    }

    fun getAllUsers(): List<User> = users.values.toList() // For debugging (optional)
}