package com.mrkevin574.chatfirebase.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mrkevin574.chatfirebase.data.model.Message
import com.mrkevin574.chatfirebase.util.CONST_MESSAGES
import com.mrkevin574.chatfirebase.util.USER_COLLECTION
import javax.inject.Inject

class MessageProvider @Inject constructor(
    private val db : FirebaseDatabase
) {
    fun getMessagesByIdForCurrentUser(localUserId : String, userReceiverId : String, callback : (List<Message>) -> Unit)
    {
        val ref = db.getReference(getUniqueIdConversation(localUserId, userReceiverId))
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
              //  callback()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun sendMessage(localUserId: String, userReceiverId: String)
    {
        val ref = db.getReference(getUniqueIdConversation(localUserId, userReceiverId))
    }

    private fun getUniqueIdConversation(userId1 : String, userId2 : String) : String
    {
        val usersIdList = listOf(userId1, userId2).sorted()
        return "${usersIdList[0]}_${usersIdList[0]}"
    }

}