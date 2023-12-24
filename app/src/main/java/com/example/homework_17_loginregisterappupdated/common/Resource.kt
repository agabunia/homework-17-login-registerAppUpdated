package com.example.homework_17_loginregisterappupdated.common

sealed class Resource<T>(
    val token: T? = null,
    val error: String? = null,
    val loading: Boolean = false
) {
    data class Success<T>(val data: T) : Resource<T>(token = data)
    data class Fail<T>(val errorMessage: String) : Resource<T>(error = errorMessage)
    data class Loading<T>(val isLoading: Boolean) : Resource<T>(loading = isLoading)
}
