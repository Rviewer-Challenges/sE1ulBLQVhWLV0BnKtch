package com.mrkevin574.chatfirebase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_NAME = "ChatFirebase"

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}