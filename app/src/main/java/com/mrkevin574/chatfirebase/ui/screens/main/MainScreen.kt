package com.mrkevin574.chatfirebase.ui.screens.main

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

    if (!mainScreenState.success) {
        Loading()
    } else if (mainScreenState.success) {
        ContainerMainScreen(navController = navController, mainScreenState.usersList, viewModel) {
            navController.navigate(Screens.ChatScreen.passIdAndName(it.first, it.second))
        }
    } else {
        ContainerErrorMainScreen()
    }

}

@Composable
fun ContainerMainScreen(navController : NavController, userList: List<User>, viewModel: MainScreenViewModel, onClick: (Pair<String, String>) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
        )
        {
            userList.forEach { user ->
                CardUser(user = user, viewModel, onClick = {onClick(Pair(user.uid, user.name))})
            }
        }
        ButtonSignOut(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp, end = 10.dp)
        ) {
            viewModel.deleteUsers()
            navController.navigate(Screens.LoginScreen.route)
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