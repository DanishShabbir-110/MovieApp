package com.danish.search_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.danish.domain.model.Movie
import com.danish.search_impl.presentation.screens.search.CineHubRed
import com.danish.search_impl.presentation.screens.search.SecondaryText

@Composable
fun SearchResults(
    movies: LazyPagingItems<Movie>, onMovieClick: (Int) -> Unit
) {
    when (movies.loadState.refresh) {
        is LoadState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = CineHubRed
                )
            }
        }

        is LoadState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Unable to load movies", color = SecondaryText, fontSize = 15.sp
                )
            }
        }

        is LoadState.NotLoading if movies.itemCount == 0 -> {

            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No movies found", color = SecondaryText, fontSize = 15.sp
                )
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(
                    start = 16.dp, end = 16.dp, bottom = 100.dp
                ), verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    count = movies.itemCount, key = { index ->
                        movies[index]?.id ?: index
                    }) { index ->

                    val movie = movies[index]

                    if (movie != null) {
                        SearchMovieItem(
                            movie = movie, onClick = {
                                onMovieClick(movie.id)
                            }
                        )
                    }
                }

                if (movies.loadState.append is LoadState.Loading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = CineHubRed
                            )
                        }
                    }
                }
            }
        }
    }
}