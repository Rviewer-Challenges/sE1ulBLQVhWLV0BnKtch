package com.mrkevin574.chatfirebase.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mrkevin574.chatfirebase.R

val familyUbuntuCondensed = FontFamily(
    Font(R.font.ubuntu_condensed)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = familyUbuntuCondensed,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)