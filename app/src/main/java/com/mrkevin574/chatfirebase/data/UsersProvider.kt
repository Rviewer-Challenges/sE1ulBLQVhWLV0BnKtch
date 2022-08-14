package com.mrkevin574.chatfirebase.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mrkevin574.chatfirebase.data.model.User
import com.mrkevin574.chatfirebase.data.model.onResponse
import com.mrkevin574.chatfirebase.util.FIELD_UID
import com.mrkevin574.chatfirebase.util.USER_COLLECTION
import javax.inject.Inject

class UsersProvider @Inject constructor(
    private val db : FirebaseFirestore
) {

    fun saveUser(user : User) = db.collection(USER_COLLECTION).document().set(user)

    fun getAllUsers(userId : String, callback : (onResponse) -> Unit) {
        val userList = mutableListOf<User>()
        db.collection(USER_COLLECTION)
            .whereNotEqualTo(FIELD_UID, userId)
            .get()
            .addOnSuccessListener { query ->
            val users = query.documents
            users.forEach {
                userList.add(it.toObject<User>()!!)
            }
                callback(onResponse(success = true, userList = userList))
        }
    }
}