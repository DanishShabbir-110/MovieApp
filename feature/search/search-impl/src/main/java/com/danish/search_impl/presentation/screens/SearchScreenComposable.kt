package com.danish.search_impl.presentation.screens

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.paging.compose.collectAsLazyPagingItems
import com.danish.navigation.Navigator
import com.danish.search_impl.presentation.navigation.SearchRoutes
import com.danish.search_impl.presentation.screens.search.SearchScreen
import com.danish.search_impl.presentation.screens.search.SearchViewModel
import org.koin.androidx.compose.koinViewModel

fun EntryProviderScope<NavKey>.searchEntry(navigator: Navigator) {
    entry<SearchRoutes.Search> {
        val viewModel: SearchViewModel = koinViewModel()
        val searchState by viewModel.uiState.collectAsStateWithLifecycle()

        val searchMovies = viewModel.searchMovies.collectAsLazyPagingItems()

        SearchScreen(
            state = searchState,
            searchMovies = searchMovies,
            effects = viewModel.effects,
            baseEffects = viewModel.baseEffects,
            onEvent = viewModel::onEvent,
            navigator = navigator
        )
    }
}