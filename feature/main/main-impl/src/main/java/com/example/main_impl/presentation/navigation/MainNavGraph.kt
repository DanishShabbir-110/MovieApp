package com.example.main_impl.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.navigation.Navigator
import com.example.main_impl.presentation.screen.mainEntry

fun EntryProviderScope<NavKey>.addMainScreen(navigator: Navigator){
    mainEntry(navigator)
}