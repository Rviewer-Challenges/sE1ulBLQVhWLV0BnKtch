package com.mrkevin574.chatfirebase.ui.screens

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrkevin574.chatfirebase.ui.theme.Screens
import com.mrkevin574.chatfirebase.R
import com.mrkevin574.chatfirebase.ui.theme.PrimaryColor

@Composable
fun LoginScreen(navController : NavController, activity : Activity, viewModel: LoginScreenViewModel = hiltViewModel()) {

    val isLoggedIn = viewModel.loginState.value

    if(isLoggedIn) navController.navigate(Screens.MainScreen.route)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeText()
        ButtonGoogle { viewModel.onEvent(activity = activity) }
    }
}

@Composable
fun WelcomeText() {
    Text(
        text = "Firebase Chat",
        color = Color.White,
        fontSize = 70.sp,
        modifier = Modifier.padding(start = 90.dp, end = 90.dp, bottom = 100.dp)
    )
}

@Composable
fun ButtonGoogle(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 80.dp, end = 80.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        ButtonGoogleContainer()
    }
}

@Composable
fun ButtonGoogleContainer() {
    Row(
        modifier = Modifier.padding(1.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.google),
            contentDescription = stringResource(R.string.google_icon),
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
        )
        Text(
            text = stringResource(com.firebase.ui.auth.R.string.fui_sign_in_with_google),
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}