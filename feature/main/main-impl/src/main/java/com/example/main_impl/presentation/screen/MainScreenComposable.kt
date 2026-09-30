package com.example.main_impl.presentation.screen

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.danish.navigation.Navigator
import com.example.main_impl.presentation.BottomNavItem
import com.example.main_impl.presentation.navigation.MainRoutes
import com.example.main_impl.presentation.screen.main.MainScreen

fun EntryProviderScope<NavKey>.mainEntry(navigator: Navigator){
    entry<MainRoutes.Main>{
        MainScreen(rootNavigator = navigator)
    }
}