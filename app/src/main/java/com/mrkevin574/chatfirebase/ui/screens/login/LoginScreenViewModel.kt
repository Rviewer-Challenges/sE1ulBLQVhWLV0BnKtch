package com.mrkevin574.chatfirebase.ui.screens.login

import android.content.IntentSender
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.mrkevin574.chatfirebase.ui.data.GoogleSignInService
import com.mrkevin574.chatfirebase.ui.theme.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val service : GoogleSignInService
) : ViewModel() {

    fun getIntentSender( intentSender : (IntentSender) -> Unit)
    {
        service.getIntentSender(launchRequest = intentSender)
    }

    fun onResult(result : ActivityResult, navController: NavController)
    {
        result.data?.let { intent ->
            service.signInWithInFirebase(intent){ resp ->
                if(resp) navController.navigate(Screens.MainScreen.route)
                else ""
            }
        }
    }
}
