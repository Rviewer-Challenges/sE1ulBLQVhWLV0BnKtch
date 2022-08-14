package com.mrkevin574.chatfirebase.ui.screens

sealed class Screens(val route : String)
{
    object MainScreen : Screens("main")
    object SplashScreen : Screens("splash")
    object LoginScreen : Screens("login")
    object ChatScreen : Screens("chat/{$PARAMETER_RECEIVER_ID}") {
        fun passId(uid : String) = "chat/$uid"
    }
}

const val PARAMETER_RECEIVER_ID = "receiverId"
