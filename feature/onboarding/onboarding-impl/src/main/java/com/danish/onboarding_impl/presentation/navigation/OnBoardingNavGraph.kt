package com.danish.onboarding_impl.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.navigation.Navigator
import com.danish.onboarding_impl.presentation.screen.onBoardingEntry

fun EntryProviderScope<NavKey>.addOnBoardingScreen(navigator: Navigator,onBoardingCompleted: () -> Unit) {
    onBoardingEntry(navigator = navigator,onBoardingCompleted = onBoardingCompleted)
}