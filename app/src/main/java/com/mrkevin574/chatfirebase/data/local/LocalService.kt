package com.mrkevin574.chatfirebase.data.local

import com.google.firebase.auth.FirebaseAuth
import com.mrkevin574.chatfirebase.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalService @Inject constructor(
    private val dao : UserDao
) {
    fun getAllUsers(): Flow<List<User>> = dao.getAll().map { users ->
        users.map { it.toDomain() }
    }

    suspend fun saveOrUpdateUser(user: User) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        uid?.let {
            if(user.uid != it)
                dao.saveUser(user.toEntity())
        }

    }

    fun getMessagesById(userId: String) = dao.getMessagesById(userId = userId)

    suspend fun deleteAllUsers() = dao.deleteAll()
}