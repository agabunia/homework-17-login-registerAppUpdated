package com.example.homework_17_loginregisterappupdated.presentation.loginPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_17_loginregisterappupdated.common.Resource
import com.example.homework_17_loginregisterappupdated.datastore.DataStoreUtil
import com.example.homework_17_loginregisterappupdated.domain.login.LoginRepository
import com.example.homework_17_loginregisterappupdated.domain.login.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repository: LoginRepository) : ViewModel() {

    private val _userFlow =
        MutableStateFlow<Resource<LoginResponse>>(Resource.Success(data = LoginResponse("")))
    val userFlow: SharedFlow<Resource<LoginResponse>> = _userFlow.asStateFlow()

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _userFlow.value = Resource.Fail(errorMessage = "All fields must be filled")
        } else if (!checkMail(email)) {
            _userFlow.value = Resource.Fail(errorMessage = "Email must be valid")
        } else {
            _userFlow.value = Resource.Loading(isLoading = true)
            viewModelScope.launch {
                repository.login(email, password).collect {
                    when (it) {
                        is Resource.Success -> _userFlow.value = Resource.Success(data = it.data)
                        is Resource.Fail -> _userFlow.value =
                            Resource.Fail(errorMessage = it.errorMessage)

                        is Resource.Loading -> _userFlow.value =
                            Resource.Loading(isLoading = it.isLoading)
                    }
                }
            }
        }
    }

    private fun checkMail(mail: String): Boolean {
        val regex = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}".toRegex()
        return regex.matches(mail)
    }

    private val _successFlow = MutableSharedFlow<LoginFragmentNavigationEvent>()
    val successFlow: SharedFlow<LoginFragmentNavigationEvent> get() = _successFlow

    fun setEmail(email: String) {
        viewModelScope.launch {
            DataStoreUtil.setEmail(email = email)
            _successFlow.emit(LoginFragmentNavigationEvent.NavigateToHome)
        }
    }

}
