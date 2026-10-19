package com.danish.shared.themes

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


object AppTextStyles {
    val Display = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold)
    val Title = TextStyle(fontSize = 20.sp, lineHeight = 24.sp, fontWeight = FontWeight.Bold)
    val Heading = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
    val Subtitle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    val Body = TextStyle(fontSize = 15.sp, lineHeight = 24.sp)
    val BodyCompact = TextStyle(fontSize = 15.sp)
    val BodySmall = TextStyle(fontSize = 14.sp)
    val Caption = TextStyle(fontSize = 12.sp, lineHeight = 16.sp)
    val Label = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Medium)
    val Button = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
}