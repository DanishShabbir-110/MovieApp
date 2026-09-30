package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenreSection(
    genres: List<String>,
    modifier: Modifier = Modifier
) {
    if (genres.isEmpty()) return

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Genres",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            genres.forEach { genre ->
                GenreChip(genre = genre)
            }
        }
    }
}

@Composable
private fun GenreChip(
    genre: String
) {
    Surface(
        shape = RoundedCornerShape(50),
        color = Color(0xFF151C23),
        border = BorderStroke(
            width = 1.dp,
            color = Color(0xFF1E2630)
        )
    ) {
        Text(
            text = genre,
            color = Color.White.copy(alpha = 0.90f),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        )
    }
}
