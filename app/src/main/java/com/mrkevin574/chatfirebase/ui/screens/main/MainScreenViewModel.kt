package com.mrkevin574.chatfirebase.ui.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.mrkevin574.chatfirebase.data.UsersRepository
import com.mrkevin574.chatfirebase.data.model.Message
import com.mrkevin574.chatfirebase.data.model.PendingMessages
import com.mrkevin574.chatfirebase.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    repository : UsersRepository
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


    fun getLastMessageAndPendingMessages(messages : List<Message>) :  PendingMessages
    {
        if(messages.isEmpty()) return PendingMessages()
        val pendingMessages = messages.filter { !it.viewed && it.ownerId != FirebaseAuth.getInstance().currentUser!!.uid }
        val lastMessage = messages.last()



        return PendingMessages(
            lastMessage = lastMessage.value,
            hourLastMessage = getTimeAgo(lastMessage.hour),
            countPending = pendingMessages.count())
    }

}

fun getTimeAgo(time : Long) : String
{
    val timeAgo = (Date().time - time) / 1000
    return if(timeAgo < 60) return "now"
    else if(timeAgo in 61..3599) return "${timeAgo/60} mins"
    else if(timeAgo in 3600..86399) return "${(timeAgo/60)/60} hours"
    else  "${((timeAgo/60)/60)/60} days"
}