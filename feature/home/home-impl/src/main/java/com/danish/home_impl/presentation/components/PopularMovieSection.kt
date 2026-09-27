package com.danish.home_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.danish.domain.model.Movie
import com.danish.shared.components.MovieCard

private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

@Composable
fun PopularMovieSection(
    movies: LazyPagingItems<Movie>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Popular Movies",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
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
                    CircularProgressIndicator()
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
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}