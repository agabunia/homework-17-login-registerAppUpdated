package com.example.homework_17_loginregisterappupdated.data.login


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("Content-Type: application/json")
    @POST("https://reqres.in/api/login")
    suspend fun login(@Body userLogin: LoginUserDto): Response<LoginDto>
}
