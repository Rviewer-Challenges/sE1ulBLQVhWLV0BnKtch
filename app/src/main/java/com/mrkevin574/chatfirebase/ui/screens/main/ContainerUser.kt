package com.mrkevin574.chatfirebase.ui.screens.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.mrkevin574.chatfirebase.data.model.User

@Composable
fun ContainerUser(user : User, onClick : (uid : String) -> Unit) {
    Text(user.uid)
}