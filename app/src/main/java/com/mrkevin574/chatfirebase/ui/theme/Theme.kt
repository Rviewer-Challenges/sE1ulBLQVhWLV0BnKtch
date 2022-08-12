package com.mrkevin574.chatfirebase.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun ChatFirebaseTheme(content: @Composable () -> Unit) {
    val colors = lightColors(
        primary = PrimaryColor,
        primaryVariant = PrimaryLightColor,
        secondary = SecondaryColor)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}