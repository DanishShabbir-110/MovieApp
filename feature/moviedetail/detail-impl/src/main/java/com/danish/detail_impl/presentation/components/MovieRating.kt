package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun MovieRating(
    rating: Double,
    voteCount: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = null,
            tint = Color(0xFFFFC107),
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = String.format(Locale.US, "%.1f", rating),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "/10",
            color = Color.White.copy(alpha = 0.55f),
            fontSize = 13.sp
        )

        if (voteCount > 0) {
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "(${formatVoteCount(voteCount)})",
                color = Color.White.copy(alpha = 0.55f),
                fontSize = 13.sp
            )
        }
    }
}

private fun formatVoteCount(voteCount: Int): String {
    return when {
        voteCount >= 1_000_000 ->
            String.format(Locale.US, "%.1fM", voteCount / 1_000_000.0)

        voteCount >= 1_000 ->
            String.format(Locale.US, "%.1fK", voteCount / 1_000.0)

        else -> voteCount.toString()
    }
}