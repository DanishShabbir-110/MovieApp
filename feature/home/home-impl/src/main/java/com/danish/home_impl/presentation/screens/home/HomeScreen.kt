package com.danish.home_impl.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.danish.home_impl.presentation.components.HeroCarousel
import com.danish.home_impl.presentation.components.HomeHeader
import com.danish.home_impl.presentation.components.MovieSection
import com.danish.home_impl.presentation.components.PopularMovieSection
import com.danish.navigation.Navigator
import com.danish.shared.base.BaseScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigator: Navigator,
) {
    val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()

    BaseScreen(
        viewModel = viewModel,
        navigator = navigator
    ) { paddingValues, state, onEvent ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item { HomeHeader() }
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
                    onMovieClick = {movieId ->
                        onEvent(HomeEvent.MovieClicked(movieId))
                    }
                )
            }
            item {
                PopularMovieSection(
                    movies = popularMovies,
                    onMovieClick = {movieId ->
                        onEvent(HomeEvent.MovieClicked(movieId))
                    }
                )
            }
        }
    }
}