package com.mrkevin574.chatfirebase.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mrkevin574.chatfirebase.R
import java.util.*

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    ContentSplashScreen()
    LaunchedEffect(key1 = "1"){
        viewModel.getSession().collect {
            if(it != null)
            {
                if(it)
                {
                    navController.navigate(Screens.MainScreen.route)
                }
            }else{
                val auth = FirebaseAuth.getInstance()
                val value = auth.currentUser == null
                if(value)  navController.navigate(Screens.LoginScreen.route)
                else {
                    navController.navigate(Screens.MainScreen.route)
                    viewModel.saveSession(true)
                }
            }
        }
    }



}

@Composable
fun ContentSplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        Image(painter = painterResource(id = R.drawable.firebase_logo), contentDescription = stringResource(
            id = R.string.firebase_logo
        ))
    }
}