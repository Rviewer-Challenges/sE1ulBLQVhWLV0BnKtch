package com.mrkevin574.chatfirebase.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val Preferences_User = "preferences_user"

val Context.dataStore  by preferencesDataStore(name = Preferences_User)

const val KEY_SESSION = "session"

class ServiceDataStore @Inject constructor(
    @ApplicationContext private val context : Context
)
{
    suspend fun saveSession(value : Boolean)
    {
        context.dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(KEY_SESSION)] = value
        }
    }

    fun getSession()  =
        context.dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(KEY_SESSION)]
        }

}