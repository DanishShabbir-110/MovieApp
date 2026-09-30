package com.danish.home_impl.presentation.screens

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.paging.compose.collectAsLazyPagingItems
import com.danish.home_impl.presentation.navigation.HomeRoutes
import com.danish.home_impl.presentation.screens.home.HomeEffect
import com.danish.home_impl.presentation.screens.home.HomeScreen
import com.danish.home_impl.presentation.screens.home.HomeState
import com.danish.home_impl.presentation.screens.home.HomeViewModel
import com.danish.navigation.Navigator
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.homeEntry(navigator: Navigator,onMovieClick:(Int)->Unit) {
    entry<HomeRoutes.Home> {
        val viewModel: HomeViewModel = koinViewModel()
        val homeUiState: HomeState by viewModel.uiState.collectAsStateWithLifecycle()
        val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()
        HomeScreen(
            state = homeUiState,
            popularMovies = popularMovies,
            effects = viewModel.effects,
            baseEffects = viewModel.baseEffects,
            onEvent = viewModel::onEvent,
            navigator = navigator,
            onEffect={effect->
                when(effect){
                    is HomeEffect.OpenMovieDetail ->{
                        onMovieClick(effect.movieId)
                    }
                }
            }
        )
    }
}