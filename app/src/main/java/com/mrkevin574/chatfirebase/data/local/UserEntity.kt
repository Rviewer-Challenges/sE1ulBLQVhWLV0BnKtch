package com.mrkevin574.chatfirebase.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mrkevin574.chatfirebase.data.model.Message
import com.mrkevin574.chatfirebase.data.model.User

@Entity(tableName = "users_table")
data class UserEntity(
    val name : String,
    @PrimaryKey val uid : String,
    val messages : String)
{
    fun toDomain() : User
    {
        return User(
            name = name,
            uid = uid,
            messages = convertMessagesJsonToObject(messages)
        )
    }
}

fun convertMessagesJsonToObject(messagesJson: String) : List<Message>
{
    val myType = object : TypeToken<List<Message>>() {}.type
    return Gson().fromJson(messagesJson, myType)
}


