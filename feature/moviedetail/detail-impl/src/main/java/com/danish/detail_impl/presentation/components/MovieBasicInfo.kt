package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun MovieBasicInfo(
    releaseDate: String,
    runtime: Int?
) {
    val year = releaseDate
        .takeIf { it.length >= 4 }
        ?.take(4)
        .orEmpty()

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (year.isNotBlank()) {
            Text(
                text = year,
                color = Color.White.copy(alpha = 0.70f),
                fontSize = 14.sp
            )
        }

        if (year.isNotBlank() && runtime != null) {
            InfoSeparator()
        }

        runtime?.let {
            Text(
                text = formatRuntime(it),
                color = Color.White.copy(alpha = 0.70f),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun InfoSeparator() {
    Text(
        text = "  |  ",
        color = Color.White.copy(alpha = 0.45f),
        fontSize = 14.sp
    )
}

private fun formatRuntime(runtime: Int): String {
    val hours = runtime / 60
    val minutes = runtime % 60

    return when {
        hours > 0 && minutes > 0 -> "${hours}h ${minutes}m"
        hours > 0 -> "${hours}h"
        else -> "${minutes}m"
    }
}