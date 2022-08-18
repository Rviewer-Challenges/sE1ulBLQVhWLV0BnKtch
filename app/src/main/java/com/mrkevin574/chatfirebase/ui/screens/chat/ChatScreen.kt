package com.mrkevin574.chatfirebase.ui.screens.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
)
{
    viewModel.getMessagesById(receiverId)
    val messages = viewModel.messages.value
    val text = remember {mutableStateOf("")}


    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.SpaceBetween) {
        HeaderChat(name = nameReceiver)
        {
            navController.popBackStack()
            navController.navigate(Screens.MainScreen.route)
        }
        ColumnMessages(localUid = viewModel.getUserId(), messages = messages){
            viewModel.makeMessageView(userReceiverId = receiverId, it)
        }

        ContainerSendMessage(text) {
            viewModel.sendMessage(value = text.value, receiverId = receiverId)
            text.value = ""
        }
    }
}

@Composable
fun ColumnMessages(localUid : String, messages : List<Message>, onRead : (Message) -> Unit) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 50.dp)) {
        messages.forEach {
            item {
                ContainerMessage(localUid = localUid, message = it, onRead =onRead)
            }
        }
    }
}



