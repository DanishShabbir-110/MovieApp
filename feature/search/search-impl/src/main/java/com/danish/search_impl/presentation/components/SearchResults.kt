package com.danish.search_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.danish.domain.model.Movie
import com.danish.shared.components.CenteredMessage
import com.danish.shared.components.LoadingIndicator

@Composable
fun SearchResults(
    movies: LazyPagingItems<Movie>, onMovieClick: (Int) -> Unit
) {
    when (movies.loadState.refresh) {
        is LoadState.Loading -> LoadingIndicator(modifier = Modifier.fillMaxSize())
        is LoadState.Error -> CenteredMessage("Unable to load movies")
        is LoadState.NotLoading if movies.itemCount == 0 -> CenteredMessage("No movies found")
        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    count = movies.itemCount,
                    key = { index -> movies[index]?.id ?: index }
                ) { index ->
                    movies[index]?.let { movie ->
                        SearchMovieItem(
                            movie = movie,
                            onClick = { onMovieClick(movie.id) }
                        )
                    }
                }

                if (movies.loadState.append is LoadState.Loading) {
                    item {
                        LoadingIndicator(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp))
                    }
                }
            }
        }
    }
}