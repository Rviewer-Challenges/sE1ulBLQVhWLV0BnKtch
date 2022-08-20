package com.mrkevin574.chatfirebase.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mrkevin574.chatfirebase.ui.screens.PARAMETER_RECEIVER_ID
import com.mrkevin574.chatfirebase.ui.screens.PARAMETER_RECEIVER_NAME
import com.mrkevin574.chatfirebase.ui.screens.chat.ChatScreen
import com.mrkevin574.chatfirebase.ui.screens.login.LoginScreen
import com.mrkevin574.chatfirebase.ui.screens.main.MainScreen
import com.mrkevin574.chatfirebase.ui.screens.SplashScreen
import com.mrkevin574.chatfirebase.ui.screens.Screens

@Composable
fun ChatNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route )
    {
        composable(Screens.SplashScreen.route) { SplashScreen(navController = navController) }
        composable(Screens.MainScreen.route) { MainScreen(navController = navController) }
        composable(Screens.LoginScreen.route) { LoginScreen(navController = navController) }
        composable(Screens.ChatScreen.route)
        { backStackEntry ->
            backStackEntry.arguments?.let {
                val uid  = it.getString(PARAMETER_RECEIVER_ID)
                val name = it.getString(PARAMETER_RECEIVER_NAME)
                if(uid != null && name != null)
                {
                    ChatScreen(navController = navController, receiverId = uid, nameReceiver = name)
                }
            }
        }
    }
}