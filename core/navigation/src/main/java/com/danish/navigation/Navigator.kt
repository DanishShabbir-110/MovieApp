package com.danish.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

class Navigator(navKey: NavKey) {
    val backStack=mutableListOf(navKey)

    fun navigateTo(key:NavKey){
        backStack.add(key)
    }

    fun navigateBack(key: NavKey?){
        if(key==null||backStack.lastOrNull()==null) backStack.removeLastOrNull()
    }

    fun popAndNavigate(navKey: NavKey){
        backStack.removeLastOrNull()
        backStack.add(navKey)
    }

    fun navigateAndPopTo(
        key: NavKey,
        inclusive: Boolean
    ) {
        val destinationIndex = backStack.indexOfLast { it == key }

        if (destinationIndex == -1) return

        val retainedStackSize =
            if (inclusive) destinationIndex
            else destinationIndex + 1

        while (backStack.size > retainedStackSize) {
            backStack.removeLastOrNull()
        }
    }

    fun navigateToRoot() {
        while (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    fun navigateAndPopStack(key: NavKey){
        backStack.clear()
        backStack.add(key)
    }
}