package com.example.utils

fun String.isValidEmail(): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return emailRegex.matches(this)
}

fun String.isValidPassword(): Boolean {
    val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=!])(?=\\S+$).{12,}$".toRegex()
    return passwordRegex.matches(this)
}