package com.danish.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.danish.datastore.core.OnboardingPreferencesRepository
import com.danish.detail_api.MovieDetailFeatureApi
import com.danish.main_api.MainFeatureApi
import com.danish.navigation.Navigator
import com.danish.onboarding_api.OnBoardingFeatureApi
import org.koin.compose.koinInject

@Composable
fun AppNavigation() {
    val mainFeatureApi: MainFeatureApi = koinInject()
    val movieDetailFeatureApi: MovieDetailFeatureApi = koinInject()
    val onBoardingFeatureApi: OnBoardingFeatureApi = koinInject()
    val onboardingPreferencesRepository: OnboardingPreferencesRepository = koinInject()
    val isOnboardingCompleted by onboardingPreferencesRepository.isOnboardingCompleted.collectAsStateWithLifecycle(
        initialValue = null
    )
    val onboardingCompleted = isOnboardingCompleted ?: return

    val startDestination = remember(onboardingCompleted) {
        if (onboardingCompleted) {
            mainFeatureApi.mainEntry()
        } else {
            onBoardingFeatureApi.onBoardingEntry()
        }
    }
    val navigator = remember {
        Navigator(startDestination)
    }
    val entryProvider = entryProvider {
        mainFeatureApi.entryProvider(navigator, this)
        movieDetailFeatureApi.entryProvider(navigator, this)
        onBoardingFeatureApi.onBoardingEntryProvider(
            navigator = navigator,
            provider = this,
            onBoardingCompleted = {
                navigator.navigateAndPopStack(mainFeatureApi.mainEntry())
            }
        )
    }

    NavDisplay(
        backStack = navigator.backStack,
        entryProvider = entryProvider,
        onBack = {
            navigator.navigateBack(null)
        }
    )
}