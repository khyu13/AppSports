package com.example.appsports.models

data class User (
    val id: Int,
    val nombre: String,
    val email: String,
    val password: String,
    var rol: String
)