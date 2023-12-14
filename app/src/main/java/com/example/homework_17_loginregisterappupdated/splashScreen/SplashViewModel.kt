package com.example.homework_17_loginregisterappupdated.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_17_loginregisterappupdated.datastore.DataStoreUtil
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _mailFlow = MutableSharedFlow<Boolean>()
    val mailFlow: SharedFlow<Boolean> get() = _mailFlow


    fun readSession() {
        viewModelScope.launch {
            DataStoreUtil.getEmail().collect {
                _mailFlow.emit(it.isNotBlank())
            }
        }
    }
}
