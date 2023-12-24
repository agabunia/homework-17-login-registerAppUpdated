package com.example.homework_17_loginregisterappupdated.data.register


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegisterService {
    @Headers("Content-Type: application/json")
    @POST("https://reqres.in/api/register")
    suspend fun register(@Body userInfo: RegisterUserDto): Response<RegisterDto>
}