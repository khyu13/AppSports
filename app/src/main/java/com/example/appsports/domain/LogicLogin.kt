package com.example.appsports.domain

import com.example.appsports.data.LoginRepository
import com.example.appsports.models.User

class LogicLogin {
    fun comprobarLogin(email: String?, password: String): User {
        if (email.isNullOrBlank() || password.isBlank()) {
            throw IllegalArgumentException("Los campos no pueden estar vacíos.")
        }

        val user = LoginRepository.obtenerUsuarios()
            .find { it.email == email && it.password == password }
            ?: throw IllegalArgumentException("Email o contraseña incorrectos.")

        return user
    }
}