package com.danish.onboarding_api

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.navigation.FeatureApi
import com.danish.navigation.Navigator

interface OnBoardingFeatureApi : FeatureApi{
    fun onBoardingEntry(): NavKey
    fun onBoardingEntryProvider(
        navigator: Navigator,
        provider: EntryProviderScope<NavKey>,
        onBoardingCompleted:()->Unit
    )
}