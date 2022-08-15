package com.mrkevin574.chatfirebase.data

import com.mrkevin574.chatfirebase.data.model.Message
import javax.inject.Inject

class MessageProvider @Inject constructor() {
    fun getMessagesByIdForCurrentUser(localUserId : String, userReceiverId : String) : List<Message>
    {
        return emptyList()
    }
}