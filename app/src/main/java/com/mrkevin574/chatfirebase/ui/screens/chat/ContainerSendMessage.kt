package com.mrkevin574.chatfirebase.ui.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.mrkevin574.chatfirebase.ui.theme.PrimaryColor

@Composable
fun ContainerSendMessage(text: MutableState<String>, modifier: Modifier, sendMessage: () -> Unit) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(end = 10.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = text.value,
            onValueChange = {text.value = it},
            modifier = Modifier.weight(1f)
                .padding(end = 10.dp)
        )
        Image(
            imageVector = Icons.Outlined.Send,
            contentDescription = "Send",
            modifier = Modifier.size(40.dp)
                .clickable { sendMessage() },
            colorFilter = ColorFilter.tint(PrimaryColor)
        )
    }
}