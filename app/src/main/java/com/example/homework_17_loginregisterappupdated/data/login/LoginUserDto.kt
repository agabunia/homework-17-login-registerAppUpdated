package com.example.homework_17_loginregisterappupdated.data.login

import com.squareup.moshi.Json

data class LoginUserDto(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)
