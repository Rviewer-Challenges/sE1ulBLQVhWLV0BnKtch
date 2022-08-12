package com.mrkevin574.chatfirebase.ui.screens

import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.mrkevin574.chatfirebase.ui.data.GoogleSignInService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    service : GoogleSignInService
) : ViewModel() {

    val client = service.getGoogleSignInClient()

    fun onResult(result : ActivityResult, navController: NavController)
    {

    }
}
