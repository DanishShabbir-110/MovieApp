package com.danish.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.danish.detail_api.MovieDetailFeatureApi
import com.danish.main_api.MainFeatureApi
import com.danish.navigation.Navigator
import org.koin.compose.koinInject

@Composable
fun AppNavigation() {
    val mainFeatureApi: MainFeatureApi = koinInject()
    val movieDetailFeatureApi: MovieDetailFeatureApi = koinInject()
    val navigator = remember {
        Navigator(mainFeatureApi.mainEntry())
    }
    val entryProvider = entryProvider {
        mainFeatureApi.entryProvider(navigator, this)
        movieDetailFeatureApi.entryProvider(navigator,this)
    }

    NavDisplay(
        backStack = navigator.backStack,
        entryProvider = entryProvider,
        onBack = {
            navigator.navigateBack(null)
        }
    )
}