package com.danish.detail_impl.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.danish.domain.model.MovieDetail

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
            model = movie.backdropPath?.let {
                "https://image.tmdb.org/t/p/original$it"
            },
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
                            Color(0x40080D12),
                            Color(0xDC080D12),
                            Color(0xFF080D12)
                        ),
                        startY = 50f
                    )
                )
        )

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .statusBarsPadding()
                .padding(start = 16.dp, top = 8.dp)
                .size(44.dp)
                .background(
                    color = Color(0x99000000),
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.15f),
                    shape = CircleShape
                )
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

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
                model = movie.posterPath?.let {
                    "https://image.tmdb.org/t/p/w500$it"
                },
                contentDescription = "${movie.title} poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(124.dp)
                    .height(186.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(alpha = 0.20f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(Color(0xFF161C24))
            )

            Spacer(modifier = Modifier.width(18.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 4.dp)
            ) {
                Text(
                    text = movie.title,
                    color = Color.White,
                    fontSize = 24.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                MovieBasicInfo(
                    releaseDate = movie.releaseDate,
                    runtime = movie.runtime
                )

                Spacer(modifier = Modifier.height(10.dp))

                MovieRating(
                    rating = movie.rating,
                    voteCount = movie.voteCount
                )

                if (movie.tagline.isNotBlank()) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = movie.tagline,
                        color = Color.White.copy(alpha = 0.75f),
                        fontSize = 13.sp,
                        fontStyle = FontStyle.Italic,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
