package com.danish.home_impl.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.danish.domain.model.Movie
import com.danish.home_impl.presentation.components.HeroCarousel
import com.danish.home_impl.presentation.components.HomeHeader
import com.danish.home_impl.presentation.components.MovieSection
import com.danish.home_impl.presentation.components.PopularMovieSection
import com.danish.navigation.Navigator
import com.danish.shared.base.BaseEffectsOfBaseViewModel
import com.danish.shared.base.BaseScreen
import com.danish.shared.theme.AppColors
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    state: HomeState,
    popularMovies: LazyPagingItems<Movie>,
    effects: Flow<HomeEffect>,
    baseEffects: Flow<BaseEffectsOfBaseViewModel>,
    onEvent: (HomeEvent) -> Unit,
    navigator: Navigator,
    onEffect: suspend (HomeEffect) -> Unit
) {
    BaseScreen(
        state = state,
        effects = effects,
        baseEffects = baseEffects,
        onEvent = onEvent,
        navigator = navigator,
        onEffect = onEffect
    ) { state, onEvent ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .background(AppColors.Background),
            contentPadding = PaddingValues(top = 8.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            item {
                HomeHeader()
            }

            item {
                HeroCarousel(
                    movies = state.trendingMovies,
                    onMovieClick = { movieId ->
                        onEvent(HomeEvent.MovieClicked(movieId))
                    }
                )
            }

            item {
                MovieSection(
                    title = "Trending Now",
                    movies = state.trendingMovies,
                    onMovieClick = { movieId ->
                        onEvent(HomeEvent.MovieClicked(movieId))
                    }
                )
            }

            item {
                PopularMovieSection(
                    movies = popularMovies,
                    onMovieClick = { movieId ->
                        onEvent(HomeEvent.MovieClicked(movieId))
                    }
                )
            }
        }
    }
}
