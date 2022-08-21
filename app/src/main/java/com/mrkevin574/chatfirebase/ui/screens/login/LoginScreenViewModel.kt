package com.mrkevin574.chatfirebase.ui.screens.login

import android.content.IntentSender
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.mrkevin574.chatfirebase.data.GoogleSignInService
import com.mrkevin574.chatfirebase.ui.screens.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val service : GoogleSignInService
) : ViewModel() {

    fun getIntentSender( intentSender : (IntentSender) -> Unit) = service.getIntentSender(launchRequest = intentSender)

    fun onResult(result : ActivityResult, navController: NavController)
    {
        result.data?.let { intent ->
            service.signInWithInFirebase(intent){ resp ->
                if(resp) {
                    navController.navigate(Screens.MainScreen.route){
                        popUpTo(Screens.LoginScreen.route, popUpToBuilder = {
                            this.inclusive = true
                        })
                    }
                }
            }
        }
    }
}
