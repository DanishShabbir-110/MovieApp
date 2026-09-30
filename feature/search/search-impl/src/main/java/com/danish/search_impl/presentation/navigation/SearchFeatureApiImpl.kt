package com.danish.search_impl.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.navigation.Navigator
import com.danish.search_api.SearchFeatureApi

class SearchFeatureApiImpl : SearchFeatureApi {
    override fun searchEntry(): NavKey {
        return SearchRoutes.Search
    }

    override fun searchEntryProvider(
        navigator: Navigator,
        provider: EntryProviderScope<NavKey>,
        onMovieClick: (Int) -> Unit
    ) {
        provider.addSearchScreen(navigator = navigator, onMovieClick = onMovieClick)
    }

    override fun entryProvider(
        navigator: Navigator,
        provider: EntryProviderScope<NavKey>
    ) {
        provider.addSearchScreen(navigator = navigator, onMovieClick = {})
    }
}