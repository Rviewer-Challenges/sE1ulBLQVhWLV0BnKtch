package com.mrkevin574.chatfirebase.data

import com.mrkevin574.chatfirebase.data.model.User
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val provider : UsersProvider
) {

    fun saveUser(user : User)
    {
        provider.saveUser(user)
    }

    fun getAllUsers(users : (List<User>) -> Unit)
    {
        provider.getAllUsers { users(it) }
    }

}