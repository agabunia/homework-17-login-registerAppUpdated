package com.example.homework_17_loginregisterappupdated.domain.register

import com.example.homework_17_loginregisterappupdated.common.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RegisterRepository {
    suspend fun register(email: String, password: String): Flow<Resource<RegisterResponse>>
}