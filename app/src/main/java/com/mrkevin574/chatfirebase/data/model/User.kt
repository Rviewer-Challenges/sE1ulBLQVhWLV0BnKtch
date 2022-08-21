package com.mrkevin574.chatfirebase.data.model

import com.google.gson.Gson
import com.mrkevin574.chatfirebase.data.local.UserEntity

data class User(val name : String = "",val uid : String = "",var messages : List<Message> = emptyList())
{
    fun toEntity() : UserEntity
    {
        return UserEntity(
            name = name,
            uid = uid,
            messages = Gson().toJson(messages)
        )
    }

    fun toFirestore() : UserFirestore{
        return UserFirestore(
            name = name,
            uid = uid
        )
    }

}
