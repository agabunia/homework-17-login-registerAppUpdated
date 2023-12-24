package com.example.homework_17_loginregisterappupdated.data.login

import com.example.homework_17_loginregisterappupdated.domain.login.LoginResponse

fun LoginDto.toDomain(): LoginResponse {
    return LoginResponse(token = token)
}
