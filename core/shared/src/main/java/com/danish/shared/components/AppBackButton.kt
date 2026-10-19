package com.danish.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.danish.shared.theme.AppColors

@Composable
fun AppBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = AppColors.SurfaceVariant,
    bordered: Boolean = false
) {
    val base = modifier
        .size(44.dp)
        .background(color = containerColor, shape = CircleShape)
    val finalModifier = if (bordered) {
        base.border(1.dp, Color.White.copy(alpha = 0.15f), CircleShape)
    } else {
        base
    }

    IconButton(onClick = onClick, modifier = finalModifier) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = "Back",
            tint = AppColors.TextPrimary
        )
    }
}