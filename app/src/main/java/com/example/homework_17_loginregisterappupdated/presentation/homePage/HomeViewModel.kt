package com.example.homework_17_loginregisterappupdated.presentation.homePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_17_loginregisterappupdated.datastore.DataStoreUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//@HiltViewModel
class HomeViewModel: ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private fun getEmail() {
        viewModelScope.launch {
            DataStoreUtil.getEmail().collect {
                _email.value = it
            }
        }
    }

    init {
        getEmail()
    }

}