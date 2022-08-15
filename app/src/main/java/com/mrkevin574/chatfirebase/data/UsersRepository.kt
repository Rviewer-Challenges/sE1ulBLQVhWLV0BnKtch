package com.mrkevin574.chatfirebase.data

import com.google.firebase.auth.FirebaseAuth
import com.mrkevin574.chatfirebase.data.model.User
import com.mrkevin574.chatfirebase.data.model.UsersResponse
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val userProvider : UsersProvider,
    private val messageProvider: MessageProvider
) {

    fun saveUser(user : User)
    {
        userProvider.saveUser(user)
    }

    fun getAllUsers(callback : (UsersResponse) -> Unit)
    {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null)
        {
            userProvider.getAllUsers(currentUser.uid) { userResponse ->
                userResponse.userList.map {
                    it.messages = messageProvider.getMessagesByIdForCurrentUser(it.uid, currentUser.uid)
                }
                callback(userResponse)
            }
        }else{
            callback(UsersResponse(success = false, userList = emptyList()))
        }

    }

}