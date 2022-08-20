package com.mrkevin574.chatfirebase.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users_table")
    fun getAll() : Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user : UserEntity)

    @Query("DELETE FROM users_table")
    suspend fun deleteAll()

    @Update
    suspend fun updateUser(user : UserEntity)

    @Query("SELECT messages FROM users_table WHERE uid=:userId")
    fun getMessagesById(userId : String) : Flow<String>
}