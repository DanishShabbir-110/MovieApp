package com.danish.detail_impl.presentation.components

import androidx.compose.runtime.Composable
import com.danish.detail_impl.presentation.screen.moviedetail.DetailEvent
import com.danish.detail_impl.presentation.screen.moviedetail.DetailState

@Composable
fun DetailContent(
    state: DetailState,
    onEvent: (DetailEvent) -> Unit,
) {
    val movie = state.movie

    when {
        movie != null -> {
            MovieDetailContent(
                movie = movie,
                onBackClick = {
                    onEvent(DetailEvent.BackClicked)
                },
                onWebsiteClick = {
                    onEvent(DetailEvent.WebsiteClicked)
                }
            )
        }

        state.errorMessage != null && !state.isLoading -> {
            DetailErrorContent(
                message = state.errorMessage,
                onRetryClick = {
                    onEvent(DetailEvent.Retry)
                },
                onBackClick = {
                    onEvent(DetailEvent.BackClicked)
                }
            )
        }
    }
}