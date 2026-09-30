package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danish.domain.model.MovieDetail

@Composable
fun MovieInfoSection(
    movie: MovieDetail,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = movie.title,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        if (movie.tagline.isNotBlank()) {
            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = movie.tagline,
                color = Color.White.copy(alpha = 0.65f),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}