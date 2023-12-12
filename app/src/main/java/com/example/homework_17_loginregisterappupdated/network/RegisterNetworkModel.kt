package com.example.homework_17_loginregisterappupdated.network

import com.example.homework_17_loginregisterappupdated.service.RegisterService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RegisterNetworkModel {

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

    fun register(): RegisterService {
        return retrofit.create(RegisterService::class.java)
    }
}
