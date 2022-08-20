package com.mrkevin574.chatfirebase.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrkevin574.chatfirebase.data.model.Message
import com.mrkevin574.chatfirebase.ui.theme.PrimaryLightColor
import com.mrkevin574.chatfirebase.ui.theme.SecondaryColor
import com.mrkevin574.chatfirebase.ui.theme.TextColorLastMessage

@Composable
fun ContainerMessage(viewModel: ChatScreenViewModel, localUid: String, message: Message, onRead: (Message) -> Unit) {
    val messageColor = if (message.ownerId == localUid){
        SecondaryColor
    } else {
        if(!message.viewed){ onRead(message) }
        PrimaryLightColor
    }
    val alignmentMessage = if (message.ownerId == localUid) Alignment.CenterEnd else Alignment.CenterStart
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 30.dp, start = 30.dp, top = 10.dp),
        contentAlignment = alignmentMessage
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .background(messageColor)
        )
        {
            Text(
                text = message.value,
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(top = 10.dp, end = 20.dp, start = 20.dp, bottom = 2.dp)
            )
            Text(
                text = viewModel.getTimeAgo(message.hour),
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                color = TextColorLastMessage,
                modifier = Modifier
                    .padding(top = 1.dp, end = 20.dp, start = 20.dp, bottom = 10.dp)
            )
        }

    }

}