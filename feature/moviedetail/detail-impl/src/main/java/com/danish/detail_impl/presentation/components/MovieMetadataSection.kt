package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.danish.domain.model.MovieDetail
import com.danish.shared.components.AppText
import com.danish.shared.components.SectionTitle
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppShapes
import com.danish.shared.theme.AppTextStyles
import com.danish.shared.util.formatRuntime
import com.danish.shared.util.formatVoteCount
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
        SectionTitle(title = "Movie Details")

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            shape = AppShapes.Large,
            color = AppColors.Surface,
            border = BorderStroke(width = 1.dp, color = AppColors.Border)
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
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
                color = AppColors.TextPrimary.copy(alpha = 0.06f),
                thickness = 1.dp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(
                text = title,
                style = AppTextStyles.BodySmall,
                color = AppColors.TextPrimary.copy(alpha = 0.55f)
            )
            AppText(
                text = value,
                style = AppTextStyles.BodySmall.copy(fontWeight = FontWeight.SemiBold),
                color = AppColors.TextPrimary.copy(alpha = 0.90f)
            )
        }
    }
}

private fun buildMetadata(movie: MovieDetail): List<MetadataItem> {
    return buildList {
        if (movie.releaseDate.isNotBlank()) {
            add(MetadataItem(title = "Release Date", value = movie.releaseDate))
        }
        movie.runtime?.let { runtime ->
            add(MetadataItem(title = "Runtime", value = formatRuntime(runtime)))
        }
        if (movie.status.isNotBlank()) {
            add(MetadataItem(title = "Status", value = movie.status))
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
            add(MetadataItem(title = "Votes", value = formatVoteCount(movie.voteCount)))
        }
    }
}
private data class MetadataItem(
    val title: String,
    val value: String
)
