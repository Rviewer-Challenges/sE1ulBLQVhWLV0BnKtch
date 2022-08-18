package com.mrkevin574.chatfirebase.ui.screens.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrkevin574.chatfirebase.data.model.Message
import com.mrkevin574.chatfirebase.ui.screens.Screens


@Composable
fun ChatScreen(
    navController: NavController,
    receiverId: String,
    viewModel: ChatScreenViewModel = hiltViewModel(),
    nameReceiver: String,
) {
    viewModel.getMessagesById(receiverId)
    val messages = viewModel.messages.value
    val text = remember { mutableStateOf("") }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (header, messagesContainer, containerSendMessage) = createRefs()


        HeaderChat(name = nameReceiver, modifier = Modifier.constrainAs(header)
        {
            top.linkTo(parent.top)
        })
        {
            navController.popBackStack()
            navController.navigate(Screens.MainScreen.route)
        }
        ColumnMessages(localUid = viewModel.getUserId(), messages = messages,
            modifier = Modifier.constrainAs(messagesContainer)
            {
                top.linkTo(header.bottom)
                bottom.linkTo(containerSendMessage.top)
            }) {
            viewModel.makeMessageView(userReceiverId = receiverId, it)
        }

        ContainerSendMessage(text,
            modifier = Modifier.constrainAs(containerSendMessage)
            {
                bottom.linkTo(parent.bottom)
            }) {
            viewModel.sendMessage(value = text.value, receiverId = receiverId)
            text.value = ""
        }
    }
}

@Composable
fun ColumnMessages(
    localUid: String,
    messages: List<Message>,
    modifier: Modifier,
    onRead: (Message) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 150.dp, bottom = 60.dp)
    ) {
        messages.forEach {
            item {
                ContainerMessage(localUid = localUid, message = it, onRead = onRead)
            }
        }
    }
}



