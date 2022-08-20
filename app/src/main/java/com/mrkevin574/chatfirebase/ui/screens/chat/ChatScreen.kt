package com.mrkevin574.chatfirebase.ui.screens.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrkevin574.chatfirebase.data.model.Message
import com.mrkevin574.chatfirebase.ui.screens.Screens
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


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
        ColumnMessages(
            viewModel = viewModel,
            localUid = viewModel.getUserId(),
            messages = messages,
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
            if(text.value.isNotEmpty())
            {
                viewModel.sendMessage(value = text.value, receiverId = receiverId)
                text.value = ""
            }
        }
    }
}

@Composable
fun ColumnMessages(
    viewModel: ChatScreenViewModel,
    localUid: String,
    messages: List<Message>,
    modifier: Modifier,
    onRead: (Message) -> Unit
) {
    val lazyState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 150.dp, bottom = 60.dp),
        state = lazyState
    ) {
        messages.forEach {
            item {
                ContainerMessage(viewModel = viewModel, localUid = localUid, message = it, onRead = onRead)
            }
        }
        if(messages.isNotEmpty())
        {
            coroutineScope.launch {
                lazyState.scrollToItem(messages.lastIndex)
            }
        }
    }
}



