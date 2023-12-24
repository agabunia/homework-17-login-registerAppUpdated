package com.example.homework_17_loginregisterappupdated.data.register

import com.squareup.moshi.Json

data class RegisterUserDto(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)
