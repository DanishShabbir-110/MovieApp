package com.example.main_impl.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface MainRoutes : NavKey {

    @Serializable
    data object Main : MainRoutes
}