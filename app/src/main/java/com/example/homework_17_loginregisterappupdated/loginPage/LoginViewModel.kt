package com.example.homework_17_loginregisterappupdated.loginPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_17_loginregisterappupdated.common.Resource
import com.example.homework_17_loginregisterappupdated.datastore.DataStoreUtil
import com.example.homework_17_loginregisterappupdated.network.LoginNetworkModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _userFlow = MutableStateFlow<Resource<LoginResponse>>(
        Resource.Success(data = LoginResponse(""))
    )
    val userFlow: SharedFlow<Resource<LoginResponse>> = _userFlow.asStateFlow()

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _userFlow.value = Resource.Fail(errorMessage = "All fields must be filled")
        } else if (!checkMail(email)) {
            _userFlow.value = Resource.Fail(errorMessage = "Email must be valid")
        } else {
            _userFlow.value = Resource.Loading(isLoading = true)
            viewModelScope.launch {
                try {
                    val response: Response<LoginResponse> =
                        LoginNetworkModel.login().login(UserLogin(email, password))
                    if (response.isSuccessful) {
                        _userFlow.value =
                            Resource.Success(data = LoginResponse(response.body().toString()))
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

    fun setEmail(email: String) {
        viewModelScope.launch {
            DataStoreUtil.setEmail(email)
        }
    }

}
