package com.example.homework_17_loginregisterappupdated.data.login

import com.squareup.moshi.Json

data class LoginDto(
    @Json(name = "token")
    val token: String
)
