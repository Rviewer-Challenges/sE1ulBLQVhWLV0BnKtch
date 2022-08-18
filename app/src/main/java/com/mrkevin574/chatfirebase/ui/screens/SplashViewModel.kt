package com.mrkevin574.chatfirebase.ui.screens

import androidx.lifecycle.ViewModel
import com.mrkevin574.chatfirebase.data.local.ServiceDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val serviceDataStore: ServiceDataStore
) : ViewModel() {
    fun getSession() = serviceDataStore.getSession()
    suspend fun saveSession(value : Boolean) = serviceDataStore.saveSession(value)
}