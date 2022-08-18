package com.mrkevin574.chatfirebase.ui.screens

sealed class Screens(val route : String)
{
    object MainScreen : Screens("main")
    object SplashScreen : Screens("splash")
    object LoginScreen : Screens("login")
    object ChatScreen : Screens("chat/{$PARAMETER_RECEIVER_ID}/{$PARAMETER_RECEIVER_NAME}") {
        fun passIdAndName(uid : String, name : String) = "chat/$uid/$name"
    }
}

const val PARAMETER_RECEIVER_ID = "receiverId"
const val PARAMETER_RECEIVER_NAME = "name"
