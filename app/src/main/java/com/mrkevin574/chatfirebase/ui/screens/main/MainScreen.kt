package com.mrkevin574.chatfirebase.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrkevin574.chatfirebase.data.model.User
import com.mrkevin574.chatfirebase.ui.screens.Screens
import com.mrkevin574.chatfirebase.ui.theme.PrimaryColor

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val mainScreenState = viewModel.mainScreenState.value
    val loading = viewModel.loading.value

    if (loading) {
        Loading()
    } else if (mainScreenState.success) {
        ContainerMainScreen(mainScreenState.usersList) {
            navController.navigate(Screens.ChatScreen.passId(it))
        }
    } else {
        ContainerErrorMainScreen()
    }

}

@Composable
fun ContainerMainScreen(userList: List<User>, onClick: (uid: String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    )
    {
        userList.forEach {
            CardUser(user = it, onClick = onClick)
        }
    }

}

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    )
    {
        CircularProgressIndicator(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp),
            strokeWidth = 10.dp,
            color = PrimaryColor
        )
    }

}

@Composable
fun ContainerErrorMainScreen() {

}