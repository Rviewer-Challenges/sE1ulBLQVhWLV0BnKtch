package com.mrkevin574.chatfirebase.data

import com.google.firebase.auth.FirebaseAuth
import com.mrkevin574.chatfirebase.data.model.User
import com.mrkevin574.chatfirebase.data.model.onResponse
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val provider : UsersProvider
) {

    fun saveUser(user : User)
    {
        provider.saveUser(user)
    }

    fun getAllUsers(callback : (onResponse) -> Unit)
    {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null)
        {
            provider.getAllUsers(currentUser.uid) { callback(it) }
        }else{
            callback(onResponse(success = false, userList = emptyList()))
        }

    }

}