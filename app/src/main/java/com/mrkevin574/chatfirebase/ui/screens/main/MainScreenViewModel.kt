package com.mrkevin574.chatfirebase.ui.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.mrkevin574.chatfirebase.data.UsersRepository
import com.mrkevin574.chatfirebase.data.model.Message
import com.mrkevin574.chatfirebase.data.model.PendingMessages
import com.mrkevin574.chatfirebase.data.model.User
import com.mrkevin574.chatfirebase.domain.GetTimeAgoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: UsersRepository,
    private val getTimeAgoUseCase: GetTimeAgoUseCase
) : ViewModel() {

    private val _mainScreenState = mutableStateOf(MainScreenState())
    val mainScreenState: State<MainScreenState> = _mainScreenState

    init {
        viewModelScope.launch {
            repository.startRequestForUsers()
            repository.getListOfUsers().collect { userList ->
                _mainScreenState.value = mainScreenState.value.copy(
                    success = true,
                    usersList = organizedUserList(userList),
                    stateChanged = !mainScreenState.value.stateChanged
                )
            }
        }

    }

    private fun organizedUserList(userList: List<User>): List<User> {
        val userListWithMessages = userList.filter { it.messages.isNotEmpty() }
        val userWithoutMessages = userList.filter { it.messages.isEmpty() }
        val userListWithMessagesOrganized =
            userListWithMessages.sortedBy { it.messages.last().hour }.toMutableList()
        val listReversed = userListWithMessagesOrganized.asReversed()
        listReversed.addAll(userWithoutMessages)
        return listReversed.toList()
    }


    fun getLastMessageAndPendingMessages(messages: List<Message>): PendingMessages {
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
        if (messages.isEmpty() || currentUserId == null) return PendingMessages()

        val pendingMessages =
            messages.filter { !it.viewed && it.ownerId !=  currentUserId}
        val lastMessage = messages.last()



        return PendingMessages(
            lastMessage = lastMessage.value,
            hourLastMessage = getTimeAgoUseCase(lastMessage.hour),
            countPending = pendingMessages.count()
        )
    }

    fun deleteUsers()
    {
        viewModelScope.launch {
            repository.deleteAllUsersFromCache()
        }
    }

}




// { response ->
//            loading.value = false
//            if(response.success)
//            {
//                _mainScreenState.value = mainScreenState.value.copy(
//                    usersList = organizedUserList(response.userList),
//                    stateChanged = !mainScreenState.value.stateChanged
//                )
//            }else{
//                _mainScreenState.value = mainScreenState.value.copy(
//                    success = false
//                )
//            }
//        }