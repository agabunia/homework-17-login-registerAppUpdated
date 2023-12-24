package com.example.homework_17_loginregisterappupdated.presentation.registerPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_17_loginregisterappupdated.common.Resource
import com.example.homework_17_loginregisterappupdated.domain.register.RegisterRepository
import com.example.homework_17_loginregisterappupdated.domain.register.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val repository: RegisterRepository) : ViewModel() {

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
                 repository.register(email, password).collect{
                     when(it) {
                         is Resource.Success -> _userFlow.value = Resource.Success(data = it.data)
                         is Resource.Fail -> _userFlow.value = Resource.Fail(errorMessage = it.errorMessage)
                         is Resource.Loading -> _userFlow.value = Resource.Loading(isLoading = it.isLoading)
                     }
                 }
            }
        }
    }

    private fun checkMail(mail: String): Boolean {
        val regex = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}".toRegex()
        return regex.matches(mail)
    }
}
