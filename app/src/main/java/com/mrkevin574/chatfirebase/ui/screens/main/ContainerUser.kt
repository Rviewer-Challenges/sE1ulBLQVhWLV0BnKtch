package com.mrkevin574.chatfirebase.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.MarkChatUnread
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mrkevin574.chatfirebase.data.model.User
import com.mrkevin574.chatfirebase.ui.theme.ColorDivider
import com.mrkevin574.chatfirebase.ui.theme.PrimaryColor
import com.mrkevin574.chatfirebase.ui.theme.TextColorLastMessage
import com.mrkevin574.chatfirebase.ui.theme.familyUbuntuCondensed

@Composable
fun CardUser(
    user : User,
    viewModel: MainScreenViewModel? = null,
    onClick : (uid : String) -> Unit,
    ) {
    val pendingMessages = viewModel!!.getLastMessageAndPendingMessages(user.messages)
    val countPendingMessages = pendingMessages.countPending
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable { onClick(user.uid) },
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                imageVector = if(countPendingMessages > 0) Icons.Filled.MarkChatUnread else Icons.Filled.Chat,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .width(35.dp)
                    .height(35.dp),
                colorFilter = ColorFilter.tint(PrimaryColor)
            )
            ContainerTextAndMessage(name = user.name, message = pendingMessages.lastMessage)
            ContainerHourAndMessagesNotReaded(hour = pendingMessages.hourLastMessage, countMessages = countPendingMessages)
        }
        Divider(Modifier.height(10.dp), color = Color.Transparent)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.7.dp)
                .padding(start = 50.dp, end = 50.dp),
            color = ColorDivider
        )
    }

}

@Composable
fun ContainerTextAndMessage(name : String, message : String)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextNameUser(name = name)
        TextLastMessageAndHour(value = message, modifier = Modifier.padding(top = 7.dp))
    }
}

@Composable
fun TextNameUser(name : String) {
    Text(
        text = name,
        fontFamily = familyUbuntuCondensed,
        fontSize = 22.sp,
        textAlign = TextAlign.Center,
        color = Color.Black
    )
}

@Composable
fun ContainerHourAndMessagesNotReaded(
    hour : String, countMessages : Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 20.dp)
    ) {
        TextLastMessageAndHour(hour)
        if(countMessages > 0)
        Text(
            text = countMessages.toString(),
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Red)
                .padding(4.dp),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun TextLastMessageAndHour(value: String, modifier: Modifier = Modifier) {
    Text(
        text = value,
        color = TextColorLastMessage,
        fontSize = 14.sp,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ContainerUserPreview() {
    CardUser(user = User(
        name = "Puchica"
    ), onClick = {})
}