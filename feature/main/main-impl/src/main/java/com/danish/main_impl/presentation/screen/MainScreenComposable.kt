package com.danish.main_impl.presentation.screen

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.navigation.Navigator
import com.danish.main_impl.presentation.navigation.MainRoutes
import com.danish.main_impl.presentation.screen.main.MainScreen

fun EntryProviderScope<NavKey>.mainEntry(navigator: Navigator){
    entry<MainRoutes.Main>{
        MainScreen(rootNavigator = navigator)
    }
}