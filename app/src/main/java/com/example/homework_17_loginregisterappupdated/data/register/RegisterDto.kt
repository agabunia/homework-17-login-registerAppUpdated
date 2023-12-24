package com.example.homework_17_loginregisterappupdated.data.register

import com.squareup.moshi.Json

data class RegisterDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "token")
    val token: String
)
