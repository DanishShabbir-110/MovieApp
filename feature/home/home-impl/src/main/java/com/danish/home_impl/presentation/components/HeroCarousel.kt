package com.danish.home_impl.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.danish.domain.model.Movie
import com.danish.shared.components.PageIndicator
import com.danish.shared.util.MovieImageSize
import com.danish.shared.util.movieImageUrl
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

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
        if (heroMovies.size <= 1) {
            return@LaunchedEffect
        }

        while (true) {
            delay(AUTO_SCROLL_DELAY.milliseconds)

            val nextPage = (pagerState.currentPage + 1) % heroMovies.size
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
                backdropUrl = movieImageUrl(movie.backdropPath, MovieImageSize.W780),
                rating = movie.rating,
                onClick = {
                    onMovieClick(movie.id)
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        if (heroMovies.size > 1) {
            PageIndicator(
                pageCount = heroMovies.size,
                currentPage = pagerState.currentPage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
        }
    }
}

private const val AUTO_SCROLL_DELAY = 5_000L
private const val HERO_MOVIE_COUNT = 4
