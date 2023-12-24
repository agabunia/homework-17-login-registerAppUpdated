package com.example.homework_17_loginregisterappupdated.data.register

import com.example.homework_17_loginregisterappupdated.domain.register.RegisterResponse

fun RegisterDto.toDomain(): RegisterResponse {
    return RegisterResponse(id = id, token = token)
}