package com.mrkevin574.chatfirebase.ui.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mrkevin574.chatfirebase.data.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository : UsersRepository
): ViewModel() {

    private val _mainScreenState = mutableStateOf(MainScreenState())
    val mainScreenState : State<MainScreenState> = _mainScreenState

    val loading = mutableStateOf(true)

    init {
        repository.getAllUsers { response ->
            loading.value = false
            if(response.success)
            {
                _mainScreenState.value = mainScreenState.value.copy(
                    usersList = response.userList
                )
            }else{
                _mainScreenState.value = mainScreenState.value.copy(
                    success = false
                )
            }
        }
    }

}