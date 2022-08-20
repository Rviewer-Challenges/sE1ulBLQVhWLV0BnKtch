package com.mrkevin574.chatfirebase.data

import android.content.Context
import android.util.Log
import com.google.firebase.database.*
import com.mrkevin574.chatfirebase.data.model.Message
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MessageProvider @Inject constructor(
    @ApplicationContext private val context : Context,
    private val database: DatabaseReference
) {

    private val TAG = "MessageProvider"
    private val CHILD_MESSAGES = "MESSAGES"

    //block: suspend CoroutineScope.() -> Unit

    fun getMessagesByIdForCurrentUser(
        localUserId: String,
        userReceiverId: String,
        callback: suspend CoroutineScope.(List<Message>) -> Unit
    ) {
        val ref = database.child(CHILD_MESSAGES)
            .child(getUniqueIdConversation(localUserId, userReceiverId))
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val messages = mutableListOf<Message>()
                snapshot.children.forEach { childrenSnapshot ->
                    messages.add(childrenSnapshot.getValue(Message::class.java)!!)
                }
                CoroutineScope(Dispatchers.IO)
                    .launch {
                        callback(messages)
                    }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun makeMessagesRead(localUserId: String, userReceiverId: String, message: Message) {
            message.viewed = true
            database.child(CHILD_MESSAGES)
                .child(getUniqueIdConversation(localUserId, userReceiverId))
                .child(message.key)
                .setValue(message)
    }

    fun sendMessage(localUserId: String, userReceiverId: String, message: Message) {
        val key = database
            .child(CHILD_MESSAGES)
            .child(getUniqueIdConversation(localUserId, userReceiverId))
            .push()

        if (key.key != null) {
            message.key = key.key!!
            key.setValue(message)
                .addOnSuccessListener {
                    Log.w(TAG, "Messaged Send")
                }.addOnFailureListener {
                    Log.w(TAG, "Failed")
                }
        }


    }

    private fun getUniqueIdConversation(userId1: String, userId2: String): String {
        val usersIdList = listOf(userId1, userId2).sorted()
        return "${usersIdList[0]}_${usersIdList[0]}"
    }

}