package com.danish.main_impl.presentation.screen.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.danish.detail_api.MovieDetailFeatureApi
import com.danish.home_api.HomeFeatureApi
import com.danish.navigation.Navigator
import com.danish.search_api.SearchFeatureApi
import org.koin.compose.koinInject

@Composable
fun MainScreen(
    rootNavigator: Navigator
) {
    val homeFeatureApi: HomeFeatureApi = koinInject()
    val searchFeatureApi: SearchFeatureApi = koinInject()
    val movieDetailFeatureApi: MovieDetailFeatureApi = koinInject()

    val homeEntry = homeFeatureApi.homeEntry()
    val searchEntry = searchFeatureApi.searchEntry()

    var selectedTab by rememberSaveable {
        mutableStateOf(BottomNavItem.HOME)
    }

    val tabNavigator = remember {
        Navigator(navKey = homeEntry)
    }

    val onMovieClick: (Int) -> Unit = { movieId ->
        rootNavigator.navigateTo(
            movieDetailFeatureApi.movieDetailEntry(
                movieId = movieId
            )
        )
    }

    val tabEntryProvider = remember(
        homeFeatureApi,
        searchFeatureApi,
        tabNavigator,
        rootNavigator
    ) {
        entryProvider {
            homeFeatureApi.homeEntryProvider(
                navigator = tabNavigator,
                provider = this,
                onMovieClick = onMovieClick
            )

            searchFeatureApi.searchEntryProvider(
                navigator = tabNavigator,
                provider = this,
                onMovieClick = onMovieClick
            )
        }
    }

    BackHandler(
        enabled = selectedTab == BottomNavItem.SEARCH
    ) {
        selectedTab = BottomNavItem.HOME
        tabNavigator.navigateAndPopStack(
            key = homeEntry
        )
    }

    Scaffold(
        contentWindowInsets = WindowInsets(
            left = 0,
            top = 0,
            right = 0,
            bottom = 0
        ),
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                contentAlignment = Alignment.Center
            ) {
                BottomNavBar(
                    selectedItem = selectedTab,
                    onItemClick = { item ->
                        if (selectedTab != item) {
                            selectedTab = item

                            val destination = when (item) {
                                BottomNavItem.HOME -> homeEntry
                                BottomNavItem.SEARCH -> searchEntry
                            }

                            tabNavigator.navigateAndPopStack(
                                key = destination
                            )
                        }
                    }
                )
            }
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
        ) {
            NavDisplay(
                backStack = tabNavigator.backStack,
                entryProvider = tabEntryProvider,
                onBack = {
                    rootNavigator.navigateBack(null)
                }
            )
        }
    }
}