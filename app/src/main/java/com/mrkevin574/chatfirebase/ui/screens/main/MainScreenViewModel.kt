package com.mrkevin574.chatfirebase.ui.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.mrkevin574.chatfirebase.data.UsersRepository
import com.mrkevin574.chatfirebase.data.model.Message
import com.mrkevin574.chatfirebase.data.model.PendingMessages
import com.mrkevin574.chatfirebase.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
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
                    usersList = organizedUserList(response.userList),
                    stateChanged = !mainScreenState.value.stateChanged
                )
            }else{
                _mainScreenState.value = mainScreenState.value.copy(
                    success = false
                )
            }
        }
    }

    private fun organizedUserList(userList : List<User>) : List<User>
    {
        val userListWithMessages = userList.filter { it.messages.isNotEmpty() }
        val userWithoutMessages = userList.filter { it.messages.isEmpty() }
        val userListWithMessagesOrganized =  userListWithMessages.sortedBy { it.messages.last().hour }.toMutableList()
        val listReversed = userListWithMessagesOrganized.asReversed()
        listReversed.addAll(userWithoutMessages)
        return listReversed.toList()
    }

    fun getIconByState() : ImageVector{
        return Icons.Filled.ChatBubble
    }

    fun getLastMessageAndPendingMessages(messages : List<Message>) :  PendingMessages
    {
        if(messages.isEmpty()) return PendingMessages()
        val pendingMessages = messages.filter { !it.viewed }
        val lastMessage = messages.last()

        val hourLastMessage = Date(lastMessage.hour)

        return PendingMessages(
            lastMessage = lastMessage.value,
            hourLastMessage = hourLastMessage.toString(),
            countPending = pendingMessages.count())
    }

}