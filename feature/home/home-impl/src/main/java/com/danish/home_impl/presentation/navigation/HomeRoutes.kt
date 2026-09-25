package com.danish.home_impl.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface HomeRoutes : NavKey {
    @Serializable
    data object HomeRoute : HomeRoutes
}