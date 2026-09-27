package com.danish.home_impl.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.home_api.HomeFeatureApi
import com.danish.navigation.Navigator

class HomeFeatureApiImpl() : HomeFeatureApi {
    override fun homeEntry(): NavKey {
        return HomeRoutes.Home
    }

    override fun entryProvider(
        navigator: Navigator,
        provider: EntryProviderScope<NavKey>
    ) {
        provider.addHomeScreen(navigator)
    }
}