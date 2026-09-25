package com.danish.home_impl.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.home_api.HomeFeatureAPi
import com.danish.navigation.Navigator

class HomeFeatureApiImpl(): HomeFeatureAPi {
    override fun homeEntry(): NavKey {
        return HomeRoutes.HomeRoute
    }

    override fun entryProvider(
        navigator: Navigator,
        provider: EntryProviderScope<NavKey>
    ) {

    }
}