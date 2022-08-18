package com.mrkevin574.chatfirebase.ui.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrkevin574.chatfirebase.ui.theme.PrimaryColor

@Composable
fun HeaderChat(name : String, onClickBack : () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(PrimaryColor)
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.size(35.dp)
                .clickable { onClickBack() },
            colorFilter = ColorFilter.tint(Color.White)
        )
        Text(
            text = name,
            color = Color.White,
            fontSize = 30.sp,
            modifier = Modifier.padding(
                start = 20.dp
            )
        )
    }

}