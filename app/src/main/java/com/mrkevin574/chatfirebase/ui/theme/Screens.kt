package com.mrkevin574.chatfirebase.ui.theme

sealed class Screens(val route : String)
{
    object MainScreen : Screens("main")
    object SplashScreen : Screens("splash")
    object LoginScreen : Screens("login")
    object ChatScreen : Screens("chat")
}
