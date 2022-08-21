package com.mrkevin574.chatfirebase.data

import com.google.firebase.auth.FirebaseAuth
import com.mrkevin574.chatfirebase.data.local.LocalService
import com.mrkevin574.chatfirebase.data.model.User
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val userProvider : UsersProvider,
    private val messageProvider: MessageProvider,
    private val localService : LocalService
) {

    fun saveUser(user : User)
    {
        userProvider.saveUser(user)
    }

    suspend fun startRequestForUsers()
    {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null)
        {
            userProvider.getAllUsers(currentUser.uid) { userResponse ->
                userResponse.userList.forEach { user ->
                    messageProvider.getMessagesByIdForCurrentUser(user.uid, currentUser.uid){ messages ->
                        user.messages = messages
                        localService.saveOrUpdateUser(user)
                    }
                }

            }
        }
    }

    fun getListOfUsers() = localService.getAllUsers()

    suspend fun deleteAllUsersFromCache() = localService.deleteAllUsers()

}