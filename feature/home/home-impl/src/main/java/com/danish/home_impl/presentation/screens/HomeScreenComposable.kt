package com.danish.home_impl.presentation.screens

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.home_impl.presentation.navigation.HomeRoutes
import com.danish.home_impl.presentation.screens.home.HomeScreen
import com.danish.home_impl.presentation.screens.home.HomeViewModel
import com.danish.navigation.Navigator
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.homeEntry(navigator: Navigator) {
    entry<HomeRoutes.Home> {
        val viewModel: HomeViewModel = koinViewModel()

        HomeScreen(
            viewModel = viewModel,
            navigator=navigator
        )
    }
}
