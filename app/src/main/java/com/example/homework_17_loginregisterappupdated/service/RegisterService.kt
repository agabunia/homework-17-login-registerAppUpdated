package com.example.homework_17_loginregisterappupdated.service

import com.example.homework_17_loginregisterappupdated.registerPage.RegisterResponse
import com.example.homework_17_loginregisterappupdated.registerPage.UserInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegisterService {
    @Headers("Content-Type: application/json")
    @POST("api/register")
    suspend fun register(@Body userInfo: UserInfo): Response<RegisterResponse>
}
