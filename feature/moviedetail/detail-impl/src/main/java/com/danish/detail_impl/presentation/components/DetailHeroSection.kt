package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.danish.domain.model.MovieDetail
import com.danish.shared.components.AppBackButton
import com.danish.shared.components.AppText
import com.danish.shared.theme.AppColors
import com.danish.shared.theme.AppShapes
import com.danish.shared.theme.AppTextStyles
import com.danish.shared.util.MovieImageSize
import com.danish.shared.util.movieImageUrl

@Composable
fun DetailHeroSection(
    movie: MovieDetail,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(480.dp)
    ) {
        AsyncImage(
            model = movieImageUrl(movie.backdropPath, MovieImageSize.Original),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            AppColors.Background.copy(alpha = 0.25f),
                            AppColors.Background.copy(alpha = 0.86f),
                            AppColors.Background
                        ),
                        startY = 50f
                    )
                )
        )
        AppBackButton(
            onClick = onBackClick,
            containerColor = AppColors.Scrim,
            bordered = true,
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .padding(start = 16.dp, top = 8.dp)
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.Bottom
        ) {
            AsyncImage(
                model = movieImageUrl(movie.posterPath, MovieImageSize.W500),
                contentDescription = "${movie.title} poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(124.dp)
                    .height(186.dp)
                    .clip(AppShapes.Large)
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.20f),
                        shape = AppShapes.Large
                    )
                    .background(AppColors.SurfaceElevated)
            )

            Spacer(modifier = Modifier.width(18.dp))
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = 4.dp)) {
                AppText(
                    text = movie.title,
                    style = AppTextStyles.Title.copy(fontSize = 24.sp, lineHeight = 28.sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(10.dp))
                MovieBasicInfo(releaseDate = movie.releaseDate, runtime = movie.runtime)

                Spacer(Modifier.height(10.dp))
                MovieRating(rating = movie.rating, voteCount = movie.voteCount)

                if (movie.tagline.isNotBlank()) {
                    Spacer(Modifier.height(10.dp))

                    AppText(
                        text = movie.tagline,
                        style = AppTextStyles.BodySmall.copy(
                            fontSize = 13.sp,
                            fontStyle = FontStyle.Italic
                        ),
                        color = AppColors.TextPrimary.copy(alpha = 0.75f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
