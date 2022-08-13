package com.mrkevin574.chatfirebase.ui.screens.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrkevin574.chatfirebase.ui.theme.PrimaryColor

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginScreenViewModel = hiltViewModel()) {

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            viewModel.onResult(result, navController) // Viewmodel had control for result
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeText()
        ButtonGoogle {
            viewModel.getIntentSender { intentSender ->
                launcher.launch(IntentSenderRequest.Builder(intentSender).build())
            }
        }
    }
}