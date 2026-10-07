package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danish.domain.model.MovieDetail
import java.util.Locale
@Composable
fun MovieMetadataSection(
    movie: MovieDetail,
    modifier: Modifier = Modifier
) {
    val metadata = buildMetadata(movie)

    if (metadata.isEmpty()) return

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(18.dp)
                    .background(
                        color = CineHubRed,
                        shape = CircleShape
                    )
            )

            Spacer(Modifier.width(10.dp))

            Text(
                text = "Movie Details",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color(0xFF121820),
            border = BorderStroke(
                width = 1.dp,
                color = Color(0xFF1E2630)
            )
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
            ) {
                metadata.forEachIndexed { index, item ->
                    MetadataRow(
                        title = item.title,
                        value = item.value,
                        showDivider = index > 0
                    )
                }
            }
        }
    }
}

@Composable
private fun MetadataRow(
    title: String,
    value: String,
    showDivider: Boolean
) {
    Column {
        if (showDivider) {
            HorizontalDivider(
                color = Color.White.copy(alpha = 0.06f),
                thickness = 1.dp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = Color.White.copy(alpha = 0.55f),
                fontSize = 14.sp
            )

            Text(
                text = value,
                color = Color.White.copy(alpha = 0.90f),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

private fun buildMetadata(movie: MovieDetail): List<MetadataItem> {
    return buildList {
        if (movie.releaseDate.isNotBlank()) {
            add(
                MetadataItem(
                    title = "Release Date",
                    value = movie.releaseDate
                )
            )
        }

        movie.runtime?.let { runtime ->
            add(
                MetadataItem(
                    title = "Runtime",
                    value = formatRuntime(runtime)
                )
            )
        }

        if (movie.status.isNotBlank()) {
            add(
                MetadataItem(
                    title = "Status",
                    value = movie.status
                )
            )
        }

        if (movie.originalLanguage.isNotBlank()) {
            add(
                MetadataItem(
                    title = "Language",
                    value = movie.originalLanguage.uppercase(Locale.ROOT)
                )
            )
        }

        if (movie.voteCount > 0) {
            add(
                MetadataItem(
                    title = "Votes",
                    value = formatVoteCount(movie.voteCount)
                )
            )
        }
    }
}

private data class MetadataItem(
    val title: String,
    val value: String
)

private fun formatRuntime(runtime: Int): String {
    val hours = runtime / 60
    val minutes = runtime % 60

    return when {
        hours > 0 && minutes > 0 -> "${hours}h ${minutes}m"
        hours > 0 -> "${hours}h"
        else -> "${minutes}m"
    }
}

private fun formatVoteCount(voteCount: Int): String {
    return when {
        voteCount >= 1_000_000 -> {
            String.format(
                Locale.US,
                "%.1fM",
                voteCount / 1_000_000.0
            )
        }

        voteCount >= 1_000 -> {
            String.format(
                Locale.US,
                "%.1fK",
                voteCount / 1_000.0
            )
        }

        else -> voteCount.toString()
    }
}
private val CineHubRed = Color(0xFFFF3158)
