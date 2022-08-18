package com.mrkevin574.chatfirebase.ui.screens.chat

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ChatScreen(navController: NavController, receiverId : String, viewModel: ChatScreenViewModel = hiltViewModel()) {
    viewModel.sendMessage("Hola probando", receiverId)
}