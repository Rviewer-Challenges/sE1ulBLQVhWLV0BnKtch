package com.mrkevin574.chatfirebase.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.mrkevin574.chatfirebase.data.model.User
import com.mrkevin574.chatfirebase.util.USER_COLLECTION
import javax.inject.Inject

class UsersProvider @Inject constructor(
    private val db : FirebaseFirestore
) {

    fun saveUser(user : User) = db.collection(USER_COLLECTION).document().set(user)

    fun getAllUsers(usersMethod : (List<User>) -> Unit) {
        val userList = mutableListOf<User>()
        db.collection(USER_COLLECTION).get().addOnSuccessListener { query ->
            val users = query.documents
            users.forEach {
                userList.add(it.toObject<User>()!!)
            }
            usersMethod(userList)
        }
    }
}