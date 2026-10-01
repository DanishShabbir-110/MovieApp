package com.danish.onboarding_impl.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.navigation.Navigator
import com.danish.onboarding_api.OnBoardingFeatureApi

class OnBoardingFeatureApiImpl : OnBoardingFeatureApi {
    override fun onBoardingEntry(): NavKey {
        return OnBoardingRoutes.OnBoarding
    }

    override fun onBoardingEntryProvider(
        navigator: Navigator,
        provider: EntryProviderScope<NavKey>,
        onBoardingCompleted: () -> Unit
    ) {
        provider.addOnBoardingScreen(
            navigator = navigator,
            onBoardingCompleted = onBoardingCompleted
        )
    }

    override fun entryProvider(
        navigator: Navigator,
        provider: EntryProviderScope<NavKey>
    ) {
        provider.addOnBoardingScreen(navigator = navigator, onBoardingCompleted = {})
    }
}