package com.mrkevin574.chatfirebase.data.model

data class Message(val value : String = "", val hour : Long =0, var viewed : Boolean = false, val ownerId : String = "")

data class PendingMessages(val lastMessage: String = "",val countPending : Int = 0, val hourLastMessage : String = "")
