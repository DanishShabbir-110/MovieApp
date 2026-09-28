package com.example.main_impl.presentation.screen.main

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
import com.danish.home_api.HomeFeatureApi
import com.danish.navigation.Navigator
import com.example.main_impl.presentation.BottomNavBar
import com.example.main_impl.presentation.BottomNavItem
import org.koin.compose.koinInject

@Composable
fun MainScreen() {
    val homeFeatureApi: HomeFeatureApi = koinInject()
    var selectedItem by rememberSaveable { mutableStateOf(BottomNavItem.HOME) }

    val tabNavigator = remember { Navigator(navKey = homeFeatureApi.homeEntry()) }
    val tabEntryProvider = entryProvider {
        homeFeatureApi.entryProvider(navigator = tabNavigator, provider = this)
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
                        if (item == selectedItem) {
                            return@BottomNavBar
                        }
                        selectedItem = item
                        when (item) {
                            BottomNavItem.HOME -> tabNavigator.navigateTo(homeFeatureApi.homeEntry())
                            BottomNavItem.SEARCH -> {}
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