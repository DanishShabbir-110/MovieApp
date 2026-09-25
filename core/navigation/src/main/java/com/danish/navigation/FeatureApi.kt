package com.danish.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey

interface FeatureApi {

    fun entryProvider(navigator: Navigator, provider: EntryProviderScope<NavKey>)
}