package com.mrkevin574.chatfirebase.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mrkevin574.chatfirebase.data.local.UserDao
import com.mrkevin574.chatfirebase.data.model.User
import com.mrkevin574.chatfirebase.data.model.UsersResponse
import com.mrkevin574.chatfirebase.util.FIELD_UID
import com.mrkevin574.chatfirebase.util.USER_COLLECTION
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class UsersProvider @Inject constructor(
    private val db : FirebaseFirestore
) {

    fun saveUser(user : User) = db.collection(USER_COLLECTION).document(user.uid).set(user)

    fun getAllUsers(userId : String, callback : (UsersResponse) -> Unit) {
        val userList = mutableListOf<User>()
        db.collection(USER_COLLECTION)
            .whereNotEqualTo(FIELD_UID, userId)
            .get()
            .addOnSuccessListener { query ->
            val users = query.documents
            users.forEach {
                userList.add(it.toObject<User>()!!)
            }
                callback(UsersResponse(success = true, userList = userList))
        }
    }
}