package com.mrkevin574.chatfirebase.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.mrkevin574.chatfirebase.R

@Composable
fun SplashScreen(
    navController: NavController
) {
    ContentSplashScreen()
    LaunchedEffect(key1 = "1") {
        val auth = FirebaseAuth.getInstance()
        val route = if(auth.currentUser == null){
            Screens.LoginScreen.route
        } else {
            Screens.MainScreen.route
        }
        navController.navigate(route){
            popUpTo(Screens.SplashScreen.route, popUpToBuilder = {
                this.inclusive = true
            })
        }
    }
}

@Composable
fun ContentSplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Image(
            painter = painterResource(id = R.drawable.firebase_logo),
            contentDescription = stringResource(
                id = R.string.firebase_logo
            )
        )
    }
}