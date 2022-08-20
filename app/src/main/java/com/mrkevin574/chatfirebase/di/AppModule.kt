package com.mrkevin574.chatfirebase.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mrkevin574.chatfirebase.data.local.AppDatabase
import com.mrkevin574.chatfirebase.data.local.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirestore() = Firebase.firestore

    @Provides
    fun provideRealtimeDatabase() = Firebase.database.reference

    @Provides
    fun provideRoomDatabase(@ApplicationContext context : Context) = Room
        .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
        .build()

    @Provides
    fun provideUserDao(database : AppDatabase) = database.userDao()
}