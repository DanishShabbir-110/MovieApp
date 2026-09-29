package com.danish.search_impl.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.navigation.Navigator
import com.danish.search_impl.presentation.screens.searchEntry

fun EntryProviderScope<NavKey>.addSearchScreen(navigator: Navigator) {
    searchEntry(navigator = navigator)
}