package com.danish.home_impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.danish.domain.model.Movie
import com.danish.shared.components.MovieCard

@Composable
fun PopularMovieSection(
    movies: LazyPagingItems<Movie>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
                text = "Popular Movies",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(
                count = movies.itemCount,
                key = { index ->
                    movies[index]?.id ?: index
                }
            ) { index ->
                val movie = movies[index]

                if (movie != null) {
                    MovieCard(
                        title = movie.title,
                        posterUrl = movie.posterPath?.let { path ->
                            "$POSTER_BASE_URL$path"
                        },
                        rating = movie.rating,
                        onClick = {
                            onMovieClick(movie.id)
                        }
                    )
                }
            }

            if (movies.loadState.append is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .height(154.dp)
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = CineHubRed,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }

        if (
            movies.loadState.refresh is LoadState.Error ||
            movies.loadState.append is LoadState.Error
        ) {
            Text(
                text = "Unable to load popular movies",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 13.sp
            )
        }
    }
}
private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"
private val CineHubRed = Color(0xFFFF3158)
