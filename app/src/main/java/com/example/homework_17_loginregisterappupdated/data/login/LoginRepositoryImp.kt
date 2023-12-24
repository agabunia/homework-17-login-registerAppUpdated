package com.example.homework_17_loginregisterappupdated.data.login

import android.util.Log
import com.example.homework_17_loginregisterappupdated.common.Resource
import com.example.homework_17_loginregisterappupdated.domain.login.LoginRepository
import com.example.homework_17_loginregisterappupdated.domain.login.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(val loginService: LoginService): LoginRepository {
    override suspend fun login(email: String, password: String): Flow<Resource<LoginResponse>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            try {
                val response: Response<LoginDto> = loginService.login(LoginUserDto(email = email, password = password))
                if (response.isSuccessful) {
                    emit(Resource.Success(data = response.body()!!.toDomain()))
                    Log.d("API_Message", "success: ${response.code()} - ${response.body()}")
                } else {
                    emit(Resource.Fail(errorMessage = response.errorBody()?.toString() ?: ""))
                    Log.d("API_Message", "failed: ${response.code()} - ${response.errorBody()}")
                }
            } catch (e: Throwable) {
                emit(Resource.Fail(errorMessage = "${e.message}"))
                Log.d("API_Message", "exception: ${e.message}")
            }
            emit(Resource.Loading(isLoading = false))
        }
    }
}
