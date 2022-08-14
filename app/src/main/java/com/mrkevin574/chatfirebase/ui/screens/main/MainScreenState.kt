package com.mrkevin574.chatfirebase.ui.screens.main

import com.mrkevin574.chatfirebase.data.model.User

data class MainScreenState(val usersList : List<User> = emptyList(),val success : Boolean = true)