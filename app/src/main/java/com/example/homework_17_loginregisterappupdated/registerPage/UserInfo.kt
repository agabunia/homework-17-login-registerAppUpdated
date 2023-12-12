package com.example.homework_17_loginregisterappupdated.registerPage

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfo(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)
