package com.mrkevin574.chatfirebase.domain

import android.content.Context
import com.mrkevin574.chatfirebase.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

class GetTimeAgoUseCase @Inject constructor(
    @ApplicationContext private val context : Context
) {
    operator fun invoke(time : Long) : String
    {
        val timeAgo = (Date().time - time) / 1000
        return if (timeAgo < 60) return context.getString(R.string.now)
        else if (timeAgo in 61..3599) return "${timeAgo / 60} " + context.getString(R.string.mins)
        else if (timeAgo in 3600..86399) return "${(timeAgo / 60) / 60} " + context.getString(R.string.hours)
        else "${((timeAgo / 60) / 60) / 60} " + context.getString(R.string.days)
    }
}