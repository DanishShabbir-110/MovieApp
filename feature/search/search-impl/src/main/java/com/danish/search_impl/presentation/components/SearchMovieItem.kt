package com.danish.search_impl.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.danish.domain.model.Movie
import com.danish.search_impl.presentation.screens.search.SecondaryText
import com.danish.shared.components.AppText
import com.danish.shared.components.RatingBadge
import com.danish.shared.components.RatingBadgeSize
import com.danish.shared.components.TagChip
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppShapes
import com.danish.shared.theme.AppTextStyles
import com.danish.shared.util.MovieImageSize
import com.danish.shared.util.movieImageUrl
import com.danish.shared.util.releaseYear

@Composable
fun SearchMovieItem(
    movie: Movie,
    onClick: () -> Unit
) {
    val year = releaseYear(movie.releaseDate)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(AppShapes.Large)
            .background(AppColors.Surface)
            .border(
                border = BorderStroke(1.dp, AppColors.Border),
                shape = AppShapes.Large
            )
            .clickable(onClick = onClick)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = movieImageUrl(movie.posterPath, MovieImageSize.W342),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(84.dp)
                .height(120.dp)
                .clip(AppShapes.Medium)
                .background(AppColors.SurfaceElevated)
        )

        Spacer(modifier = Modifier.width(14.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            AppText(
                text = movie.title,
                style = AppTextStyles.Subtitle.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (year.isNotEmpty()) {
                    TagChip(text = year)
                    Spacer(modifier = Modifier.width(10.dp))
                }
                RatingBadge(
                    rating = movie.rating,
                    size = RatingBadgeSize.Large,
                    fontWeight = FontWeight.SemiBold
                )
            }

            if (movie.overview.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))
                AppText(
                    text = movie.overview,
                    style = AppTextStyles.Caption.copy(lineHeight = 16.sp),
                    color = AppColors.TextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.overview,
                    color = SecondaryText,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
