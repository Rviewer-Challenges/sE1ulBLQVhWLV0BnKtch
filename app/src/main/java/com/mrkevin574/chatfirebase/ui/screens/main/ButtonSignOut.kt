package com.mrkevin574.chatfirebase.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.firebase.auth.FirebaseAuth
import com.mrkevin574.chatfirebase.R

@Composable
fun ButtonSignOut(modifier : Modifier, onSignOut : () -> Unit) {

    val openDialog = remember { mutableStateOf(false) }

    FloatingActionButton(
        modifier = modifier,
        onClick = {
            openDialog.value = true
    }) {
        Image(
            imageVector = Icons.Outlined.ExitToApp,
            contentDescription = stringResource(R.string.sign_out)
        )
    }

    if(openDialog.value)
    {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(stringResource(R.string.sign_out)) },
            text = { Text(stringResource(id = R.string.sign_out_message))},
            confirmButton = {
                Button(onClick = {
                    FirebaseAuth.getInstance().signOut()
                    onSignOut()
                }){
                    Text(stringResource(R.string.yes))
                }
            },
            dismissButton = {
                Button(onClick = {openDialog.value = false}){
                    Text(stringResource(R.string.no))
                }
            }
        )
    }

}
