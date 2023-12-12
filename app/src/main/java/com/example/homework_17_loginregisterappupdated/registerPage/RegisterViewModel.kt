package com.example.homework_17_loginregisterappupdated.registerPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_17_loginregisterappupdated.common.Resource
import com.example.homework_17_loginregisterappupdated.network.RegisterNetworkModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val _userFlow = MutableStateFlow<Resource<RegisterResponse>>(
        Resource.Success(data = RegisterResponse(0, ""))
    )
    val userFlow: SharedFlow<Resource<RegisterResponse>> = _userFlow.asStateFlow()


    fun register(email: String, password: String, repeatPassword: String) {
        if (email.isBlank() || password.isBlank() || repeatPassword.isBlank()) {
            _userFlow.value = Resource.Fail(errorMessage = "All fields must be filled")
        } else if (password != repeatPassword) {
            _userFlow.value = Resource.Fail(errorMessage = "Passwords must be the same")
        } else if (!checkMail(email)) {
            _userFlow.value = Resource.Fail(errorMessage = "Email must be valid")
        } else {
            _userFlow.value = Resource.Loading(isLoading = true)
            viewModelScope.launch {
                try {
                    val response: Response<RegisterResponse> =
                        RegisterNetworkModel.register().register(UserInfo(email, password))
                    if (response.isSuccessful) {
                        _userFlow.value = Resource.Success(data = response.body()!!)
                        Log.d("API_Message", "success: ${response.code()} - ${response.body()}")
                    } else {
                        _userFlow.value =
                            Resource.Fail(errorMessage = response.errorBody()?.toString() ?: "")
                        Log.d("API_Message", "failed: ${response.code()} - ${response.errorBody()}")
                    }
                } catch (e: IOException) {
                    Log.d("API_Message", "exception: ${e.message}")
                } finally {
                    _userFlow.value = Resource.Loading(isLoading = false)
                }
            }
        }
    }

    private fun checkMail(mail: String): Boolean {
        val regex = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}".toRegex()
        return regex.matches(mail)
    }
}
