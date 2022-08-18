package com.mrkevin574.chatfirebase.data

import android.util.Log
import com.google.firebase.database.*
import com.mrkevin574.chatfirebase.data.model.Message
import javax.inject.Inject

class MessageProvider @Inject constructor(
    private val database : DatabaseReference
) {

    private val TAG = "MessageProvider"
    private val CHILD_MESSAGES = "MESSAGES"

    fun getMessagesByIdForCurrentUser(localUserId : String, userReceiverId : String, callback : (List<Message>) -> Unit)
    {
        val messages = mutableListOf<Message>()
        val ref = database.child(CHILD_MESSAGES).child(getUniqueIdConversation(localUserId, userReceiverId))
        ref.get().addOnSuccessListener { dataSnapshot ->
            dataSnapshot.children.forEach{ childrenSnapshot ->
                messages.add(childrenSnapshot.getValue(Message::class.java)!!)
            }
            callback(messages)
        }
    }

    fun sendMessage(localUserId: String, userReceiverId: String, message : Message)
    {
        database
            .child(CHILD_MESSAGES)
            .child(getUniqueIdConversation(localUserId, userReceiverId))
            .push()
            .setValue(message)
            .addOnSuccessListener {
                Log.w(TAG, "Messaged Send")
            }.addOnFailureListener {
                Log.w(TAG, "Failed")
            }
    }

    private fun getUniqueIdConversation(userId1 : String, userId2 : String) : String
    {
        val usersIdList = listOf(userId1, userId2).sorted()
        return "${usersIdList[0]}_${usersIdList[0]}"
    }

}