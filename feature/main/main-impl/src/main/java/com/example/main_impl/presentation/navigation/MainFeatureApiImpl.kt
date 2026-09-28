package com.example.main_impl.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.main_api.MainFeatureApi
import com.danish.navigation.Navigator

class MainFeatureApiImpl: MainFeatureApi {
    override fun mainEntry(): NavKey {
        return MainRoutes.Main
    }

    override fun entryProvider(
        navigator: Navigator,
        provider: EntryProviderScope<NavKey>
    ) {
        provider.addMainScreen(navigator)
    }
}