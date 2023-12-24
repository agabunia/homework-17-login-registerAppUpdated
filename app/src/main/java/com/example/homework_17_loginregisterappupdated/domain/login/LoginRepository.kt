package com.example.homework_17_loginregisterappupdated.domain.login

import com.example.homework_17_loginregisterappupdated.common.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(email: String, password: String): Flow<Resource<LoginResponse>>
}
