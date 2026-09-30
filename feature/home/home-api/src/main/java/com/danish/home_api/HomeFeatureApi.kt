package com.danish.home_api

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.navigation.FeatureApi
import com.danish.navigation.Navigator

interface HomeFeatureApi: FeatureApi {

    fun homeEntry(): NavKey

    fun homeEntryProvider(
        navigator: Navigator,
        provider: EntryProviderScope<NavKey>,
        onMovieClick:(Int)-> Unit
    )
}