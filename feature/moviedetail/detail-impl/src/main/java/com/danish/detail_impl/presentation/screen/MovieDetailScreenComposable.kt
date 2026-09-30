package com.danish.detail_impl.presentation.screen

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.detail_impl.presentation.navigation.MovieDetailRoutes
import com.danish.detail_impl.presentation.screen.moviedetail.DetailScreen
import com.danish.detail_impl.presentation.screen.moviedetail.MovieDetailViewModel
import com.danish.navigation.Navigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun EntryProviderScope<NavKey>.movieDetailEntry(navigator: Navigator) {
    entry<MovieDetailRoutes.MovieDetail> { route ->
        val viewModel: MovieDetailViewModel = koinViewModel(
            key = "movie_detail_${route.movieId}",
            parameters = { parametersOf(route.movieId) }
        )
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        DetailScreen(
            state = state,
            effects = viewModel.effects,
            baseEffect = viewModel.baseEffects,
            onEvent = viewModel::onEvent,
            navigator = navigator
        )
    }
}