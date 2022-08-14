package com.mrkevin574.chatfirebase.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrkevin574.chatfirebase.data.model.User
import com.mrkevin574.chatfirebase.ui.screens.Screens

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val mainScreenState = viewModel.mainScreenState.value

    if(mainScreenState.success)
    {
        ContainerMainScreen(mainScreenState.usersList){ navController.navigate(Screens.ChatScreen.passId(it)) }
    }else{
        ContainerErrorMainScreen()
    }

}

@Composable
fun ContainerMainScreen(userList : List<User>, onClick : (uid : String) -> Unit)
{
    userList.forEach {
        Text(it.name, modifier = Modifier.clickable { onClick(it.uid) })
    }
}

@Composable
fun ContainerErrorMainScreen() {

}