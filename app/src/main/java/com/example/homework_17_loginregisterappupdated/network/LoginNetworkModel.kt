package com.example.homework_17_loginregisterappupdated.network

import com.example.homework_17_loginregisterappupdated.service.LoginService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object LoginNetworkModel {
    private const val BASE_URL = "https://reqres.in/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    fun login():LoginService {
        return retrofit.create(LoginService::class.java)
    }
}