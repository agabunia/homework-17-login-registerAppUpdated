package com.example.homework_17_loginregisterappupdated.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.homework_17_loginregisterappupdated.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object DataStoreUtil {
    private val EMAIL_KEY = stringPreferencesKey("definedEmail")

    fun getEmail(): Flow<String> = App.application.applicationContext.dataStore.data
        .map { preferences ->
            preferences[EMAIL_KEY] ?: ""
        }

    suspend fun setEmail(email: String) {
        App.application.applicationContext.dataStore.edit { settings ->
            settings[EMAIL_KEY] = email
        }
    }

}
