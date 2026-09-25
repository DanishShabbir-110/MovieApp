package com.danish.home_impl.presentation.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danish.domain.model.Movie
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private const val AUTO_SCROLL_DELAY = 5_000L
private const val HERO_MOVIE_COUNT = 4

@Composable
fun HeroCarousel(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val heroMovies = movies.take(HERO_MOVIE_COUNT)

    if (heroMovies.isEmpty()) return

    val pagerState = rememberPagerState(
        pageCount = { heroMovies.size }
    )

    LaunchedEffect(pagerState, heroMovies.size) {
        if (heroMovies.size <= 1) return@LaunchedEffect

        while (true) {
            delay(AUTO_SCROLL_DELAY.milliseconds)

            val nextPage =
                (pagerState.currentPage + 1) % heroMovies.size

            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->

            val movie = heroMovies[page]

            HeroMovieCard(
                title = movie.title,
                overview = movie.overview,
                backdropUrl = movie.backdropPath?.let { path ->
                    "https://image.tmdb.org/t/p/w780$path"
                },
                rating = movie.rating,
                onClick = {
                    onMovieClick(movie.id)
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        if (heroMovies.size > 1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(heroMovies.size) { index ->

                    Surface(
                        modifier = Modifier.padding(horizontal = 3.dp),
                        shape = MaterialTheme.shapes.extraLarge,
                        color = if (index == pagerState.currentPage) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        }
                    ) {
                        androidx.compose.foundation.layout.Box(
                            modifier = Modifier.padding(
                                horizontal = if (
                                    index == pagerState.currentPage
                                ) 10.dp else 4.dp,
                                vertical = 4.dp
                            )
                        )
                    }
                }
            }
        }
    }
}