package com.danish.search_api

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.navigation.FeatureApi
import com.danish.navigation.Navigator

interface SearchFeatureApi: FeatureApi {
    fun searchEntry(): NavKey
    fun searchEntryProvider(
        navigator: Navigator,
        provider: EntryProviderScope<NavKey>,
        onMovieClick: (Int) -> Unit
    )
}