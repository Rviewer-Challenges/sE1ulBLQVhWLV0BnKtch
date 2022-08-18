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
        val ref = database.child(CHILD_MESSAGES).child(getUniqueIdConversation(localUserId, userReceiverId))
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val messages = mutableListOf<Message>()
                snapshot.children.forEach{ childrenSnapshot ->
                    messages.add(childrenSnapshot.getValue(Message::class.java)!!)
                }
                callback(messages)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun makeMessagesRead(localUserId: String, userReceiverId: String, messages : List<Message>)
    {
        messages.map {
            if(it.ownerId != localUserId && !it.viewed)
            {
                it.viewed = true
                database.child(CHILD_MESSAGES)
                    .child(getUniqueIdConversation(localUserId, userReceiverId))
                    .child(it.key)
                    .setValue(it)
            }
        }

    }

    fun sendMessage(localUserId: String, userReceiverId: String, message : Message)
    {
        val key = database
            .child(CHILD_MESSAGES)
            .child(getUniqueIdConversation(localUserId, userReceiverId))
            .push()

        if(key.key != null)
        {
            message.key = key.key!!
            key.setValue(message)
                .addOnSuccessListener {
                    Log.w(TAG, "Messaged Send")
                }.addOnFailureListener {
                    Log.w(TAG, "Failed")
                }
        }


    }

    private fun getUniqueIdConversation(userId1 : String, userId2 : String) : String
    {
        val usersIdList = listOf(userId1, userId2).sorted()
        return "${usersIdList[0]}_${usersIdList[0]}"
    }

}