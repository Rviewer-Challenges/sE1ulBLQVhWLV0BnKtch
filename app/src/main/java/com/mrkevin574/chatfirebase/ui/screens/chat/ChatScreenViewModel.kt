package com.mrkevin574.chatfirebase.ui.screens.chat

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.mrkevin574.chatfirebase.data.MessageProvider
import com.mrkevin574.chatfirebase.data.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatScreenViewModel @Inject constructor(
    private val provider : MessageProvider
) : ViewModel() {

    fun sendMessage(value : String, receiverId : String)
    {
        val message = Message(
            value = value,
            hour = Date().time,
            viewed = false
        )
        provider.sendMessage(
            localUserId = FirebaseAuth.getInstance().currentUser!!.uid,
            userReceiverId = receiverId,
            message = message
        )
    }
}