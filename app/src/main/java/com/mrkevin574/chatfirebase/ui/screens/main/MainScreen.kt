package com.mrkevin574.chatfirebase.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrkevin574.chatfirebase.ui.screens.Screens

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val mainScreenState = viewModel.mainScreenState.value

    if(mainScreenState.success)
    {
        ContainerMainScreen{ navController.navigate(Screens.ChatScreen.passId(it)) }
    }else{
        ContainerErrorMainScreen()
    }

}

@Composable
fun ContainerMainScreen(onClick : (uid : String) -> Unit) {

}

@Composable
fun ContainerErrorMainScreen() {

}