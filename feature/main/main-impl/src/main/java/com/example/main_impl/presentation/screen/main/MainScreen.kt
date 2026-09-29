package com.example.main_impl.presentation.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.danish.home_api.HomeFeatureApi
import com.danish.navigation.Navigator
import com.danish.search_api.SearchFeatureApi
import com.example.main_impl.presentation.BottomNavBar
import com.example.main_impl.presentation.BottomNavItem
import org.koin.compose.koinInject

@Composable
fun MainScreen() {
    val homeFeatureApi: HomeFeatureApi = koinInject()
    val searchFeatureApi: SearchFeatureApi = koinInject()

    val homeEntry = remember(homeFeatureApi) {
        homeFeatureApi.homeEntry()
    }

    val searchEntry = remember(searchFeatureApi) {
        searchFeatureApi.searchEntry()
    }

    val tabNavigator = remember(homeEntry) {
        Navigator(navKey = homeEntry)
    }

    val tabEntryProvider = remember(
        homeFeatureApi,
        searchFeatureApi,
        tabNavigator
    ) {
        entryProvider {
            homeFeatureApi.entryProvider(
                navigator = tabNavigator,
                provider = this
            )

            searchFeatureApi.entryProvider(
                navigator = tabNavigator,
                provider = this
            )
        }
    }

    val selectedItem = when (tabNavigator.backStack.lastOrNull()) {
        searchEntry -> BottomNavItem.SEARCH
        else -> BottomNavItem.HOME
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding(),
                contentAlignment = Alignment.Center
            ) {
                BottomNavBar(
                    selectedItem = selectedItem,
                    onItemClick = { item ->
                        when (item) {
                            BottomNavItem.HOME -> {
                                if (selectedItem != BottomNavItem.HOME) {
                                    tabNavigator.navigateAndPopTo(
                                        key = homeEntry,
                                        inclusive = false
                                    )
                                }
                            }

                            BottomNavItem.SEARCH -> {
                                if (selectedItem != BottomNavItem.SEARCH) {
                                    tabNavigator.navigateTo(searchEntry)
                                }
                            }
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
                    tabNavigator.navigateBack(null)
                }
            )
        }
    }
}