package com.mrkevin574.chatfirebase.data

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.mrkevin574.chatfirebase.R
import com.mrkevin574.chatfirebase.data.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GoogleSignInService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val usersRepository: UsersRepository
) {

    private val TAG = "GoogleSignInService"
    private val oneTapClient = Identity.getSignInClient(context)

    fun getIntentSender(launchRequest: (IntentSender) -> Unit) {
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener {
                try {
                    launchRequest(it.pendingIntent.intentSender)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
    }

    fun signInWithInFirebase(
        intent: Intent,
        onFinalized: (Boolean) -> Unit
    ) {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val idToken = credential.googleIdToken
        idToken?.let {
            val firebaseCredential = GoogleAuthProvider.getCredential(it, null)
            FirebaseAuth.getInstance().signInWithCredential(firebaseCredential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        usersRepository.saveUser(
                            User(name = credential.displayName!!, uid = FirebaseAuth.getInstance().currentUser!!.uid)
                        )
                        onFinalized(true)
                    } else {
                        onFinalized(false)
                    }
                }
        }
    }
}