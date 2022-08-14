package com.mrkevin574.chatfirebase.ui.screens.chat

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ChatScreen(navController: NavController, receiverId : String) {
    Text(receiverId)
}