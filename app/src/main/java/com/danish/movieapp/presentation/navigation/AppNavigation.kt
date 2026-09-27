package com.danish.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.danish.home_api.HomeFeatureApi
import com.danish.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun AppNavigation() {
    val homeFeatureApi: HomeFeatureApi = koinInject()
    val navigator = remember {
        Navigator(homeFeatureApi.homeEntry())
    }
    val entryProvider = entryProvider {
        homeFeatureApi.entryProvider(navigator, this)
    }

    NavDisplay(
        backStack = navigator.backStack,
        entryProvider = entryProvider,
        onBack = {
            navigator.navigateBack(null)
        }
    )
}