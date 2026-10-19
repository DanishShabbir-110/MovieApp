package com.danish.home_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.danish.domain.model.Movie
import com.danish.shared.components.AppText
import com.danish.shared.components.LoadingIndicator
import com.danish.shared.components.MovieCard
import com.danish.shared.components.SectionTitle
import com.danish.shared.theme.AppTextStyles
import com.danish.shared.util.MovieImageSize
import com.danish.shared.util.movieImageUrl

@Composable
fun PopularMovieSection(
    movies: LazyPagingItems<Movie>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SectionTitle(title = "Popular Movies", modifier = Modifier.padding(horizontal = 16.dp))

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
                movies[index]?.let { movie ->
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
            if (movies.loadState.append is LoadState.Loading) {
                item {
                    LoadingIndicator(
                        size = 24.dp,
                        modifier = Modifier
                            .height(154.dp)
                            .padding(horizontal = 8.dp)
                    )
                }
            }
        }
        if (movies.loadState.refresh is LoadState.Error || movies.loadState.append is LoadState.Error) {
            AppText(
                text = "Unable to load popular movies",
                style = AppTextStyles.BodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}