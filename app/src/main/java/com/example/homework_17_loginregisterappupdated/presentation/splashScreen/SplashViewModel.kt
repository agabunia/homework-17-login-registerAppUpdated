package com.example.homework_17_loginregisterappupdated.presentation.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_17_loginregisterappupdated.datastore.DataStoreUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

//@HiltViewModel
class SplashViewModel : ViewModel() {
    private val _sessionFlow = MutableSharedFlow<Boolean>()
    val sessionFlow: SharedFlow<Boolean> get() = _sessionFlow


    fun readSession() {
        viewModelScope.launch {
            DataStoreUtil.getEmail().collect {
                _sessionFlow.emit(it.isNotEmpty())
            }
        }
    }
}
