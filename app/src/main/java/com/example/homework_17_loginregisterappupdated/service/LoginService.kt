package com.example.homework_17_loginregisterappupdated.service

import com.example.homework_17_loginregisterappupdated.loginPage.LoginResponse
import com.example.homework_17_loginregisterappupdated.loginPage.UserLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("Content-Type: application/json")
    @POST("api/login")
    suspend fun login(@Body userLogin: UserLogin): Response<LoginResponse>
}