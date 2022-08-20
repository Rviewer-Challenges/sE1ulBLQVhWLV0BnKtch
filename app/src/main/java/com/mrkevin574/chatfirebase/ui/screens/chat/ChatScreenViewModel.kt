package com.mrkevin574.chatfirebase.ui.screens.chat

import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.mrkevin574.chatfirebase.data.MessageProvider
import com.mrkevin574.chatfirebase.data.local.LocalService
import com.mrkevin574.chatfirebase.data.local.convertMessagesJsonToObject
import com.mrkevin574.chatfirebase.data.model.Message
import com.mrkevin574.chatfirebase.ui.screens.main.MainScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val provider: MessageProvider,
    private val localService: LocalService
) : ViewModel() {

    val messages = mutableStateOf(emptyList<Message>())

    fun sendMessage(value : String, receiverId : String)
    {
        val message = Message(
            value = value.trim(),
            hour = Date().time,
            viewed = false,
            ownerId = getUserId()
        )
        provider.sendMessage(
            localUserId = getUserId(),
            userReceiverId = receiverId,
            message = message
        )
    }

    fun getUserId() = FirebaseAuth.getInstance().currentUser!!.uid

    fun getMessagesById(receiverId: String) {
        viewModelScope.launch {
            localService.getMessagesById(receiverId).collect {
                messages.value = convertMessagesJsonToObject(it)
            }
        }
    }

    fun makeMessageView(userReceiverId : String, message: Message)
    {
        provider.makeMessagesRead(getUserId(), userReceiverId = userReceiverId, message)
    }

}