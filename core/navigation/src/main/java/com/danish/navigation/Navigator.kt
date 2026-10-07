package com.danish.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavKey

class Navigator(navKey: NavKey) {
    val backStack = mutableStateListOf(navKey)

    fun navigateTo(key: NavKey) {
        backStack.add(key)
    }

    fun navigateBack(key: NavKey?) {
        if(backStack.size <= 1) return
        if (key == null || backStack.lastOrNull() == null) backStack.removeLastOrNull()
    }

    fun navigateAndPopStack(key: NavKey) {
        backStack.clear()
        backStack.add(key)
    }
}