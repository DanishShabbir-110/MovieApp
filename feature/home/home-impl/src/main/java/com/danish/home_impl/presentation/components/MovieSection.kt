package com.danish.home_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danish.domain.model.Movie
import com.danish.shared.components.MovieCard
import com.danish.shared.components.SectionTitle
import com.danish.shared.util.MovieImageSize
import com.danish.shared.util.movieImageUrl


@Composable
fun MovieSection(
    title: String,
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (movies.isEmpty()) return

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SectionTitle(title = title, modifier = Modifier.padding(horizontal = 16.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(
                items = movies,
                key = { it.id }
            ) { movie ->
                MovieCard(
                    title = movie.title,
                    posterUrl = movieImageUrl(movie.posterPath, MovieImageSize.W500),
                    rating = movie.rating,
                    onClick = {
                        onMovieClick(movie.id)
                    }
                )
            }
        }
    }
}
