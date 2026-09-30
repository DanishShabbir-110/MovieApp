package com.danish.home_impl.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.home_impl.presentation.screens.homeEntry
import com.danish.navigation.Navigator

fun EntryProviderScope<NavKey>.addHomeScreen(navigator: Navigator,onMovieClick:(Int)->Unit) {
    homeEntry(navigator = navigator,onMovieClick=onMovieClick)
}