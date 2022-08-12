package com.mrkevin574.chatfirebase.ui.screens

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor() : ViewModel() {

    fun onEvent(onFinished : (Boolean) -> Unit)
    {

    }
}